# The Liskov Substitution Principle (LSP) Part 2

## Project thực tế

Những năm đầu thập niên 1990, tác giả Robert mua một thư viện third-party có chứa một số container class liên quan tới *Bag* và *Set* của Smalltalk. Có hai biến thể của *Set* và hai biến thể tương tự của *Bag*. Biến thể đầu tiên là kiểu ràng buộc (*bounded*) dựa trên một mảng (array). Biến thể thứ hai là kiểu không ràng buộc (*unbounded*) dựa trên một danh sách liên kết (linked list).

Hàm constructor của *BoundedSet* chỉ định số lượng phần tử tối đa mà tập hợp có thể chứa. Bộ nhớ được cấp phát trước dưới dạng mảng nên chúng ta có thể biết được việc tạo *BoundedSet* có thành công hay không. Không phải cấp phát bộ nhớ trong quá trình hoạt động nên tốc độ xử lý rất nhanh. Ta cũng không phải lo lắng tiêu tốn hết bộ nhớ heap khi làm việc với *BoundedSet*. Hạn chế là lãng phí bộ nhớ khi mà chúng ta rất ít khi tận dụng hết không gian bộ nhớ được cấp phát.

*UnboundedSet* ngược lại không khai báo giới hạn phần tử. Chừng nào mà bộ nhớ heap còn trống thì nó có thể lưu thêm phần tử. Nó rất tiết kiệm bộ nhớ do chỉ cấp phát khi cần thiết. Tuy nhiên tốc độ xử lý chậm hơn do thường xuyên phải cấp phát và giải phóng bộ nhớ. Đồng thời cũng tiềm ẩn nguy cơ cạn bộ nhớ heap nếu cấp phát liên tục.

Tác giả không muốn phụ thuộc vào interface của third-party class nên đã đóng gói third-party container vào trong một interface như trong hình 10-2.

![image](https://user-images.githubusercontent.com/27339791/95930974-2477a680-0df2-11eb-8ce5-d7cd7695d1b8.png)

Interface *Set* có 3 phương thức abstract là *Add, Delete,* và *IsMember* như trong hình 10-4. Cấu trúc này đồng nhất hai biến thể *bounded* và *unbounded* của hai tập hợp và cho phép truy cập chúng thông qua một interface chung. Vì vậy một số client có thể sử dụng tham số kiểu *Set* mà không biết nó là biến thể *bounded* hay *unbounded*.

![image](https://user-images.githubusercontent.com/27339791/95931008-440ecf00-0df2-11eb-8749-0f6693feeb55.png)

Lợi ích lớn nhất là không cần quan tâm kiểu *Set* là gì. Người lập trình có thể quyết định kiểu *Set* mà không ảnh hưởng tới các phương thức xử lý. Khi bộ nhớ thấp và yêu cầu tốc độ không cao, anh ta có thể lựa chọn *UnboundedSet*, còn khi bộ nhớ thoải mái và yêu cầu tốc độ cao thì lựa chọn *BoundedSet*. Các phương thức xử lý object thông qua interface của lớp cơ sở *Set* mà không biết kiểu *Set* là gì.

## Vấn đề

Tác giả muốn thêm một *PersistentSet* vào thiết kế. Một persistent set có thể được truyền vào một stream sau đó nhận lại, có thể ở một ứng dụng khác. Thật không may là chỉ có duy nhất một third-party container chấp nhận các object được kế thừa từ abstract base class *PersistentObject*. Robert thiết kế một phân cấp như hình 10-3.

![image](https://user-images.githubusercontent.com/27339791/96058474-82b98d80-0eb5-11eb-977d-cd1eaf64c411.png)

Để ý là *PersistentSet* chứa một instance của third-party persistent set (quan hệ composition). Khi ta gọi phương thức *Add* của *PersistentSet*, nó đơn giản là gọi tiếp hàm tương ứng trong third-party persistent set.

Thoạt nhìn thiết kế có vẻ ổn. Nhưng có vấn đề là các phần tử thêm vào *PersistentSet* bắt buộc phải kế thừa từ third-party *PersistentObject* do third-party persistent set chỉ làm việc với chúng. Trước đó, interface *Set* không có ràng buộc này.

Khi client thêm một phần tử vào lớp cơ sở *Set*, họ không hề biết nó phải là dẫn xuất của *PersistentObject*. Xem xét đoạn code ở hình 10-6. Rõ ràng khi client thêm một object không thuộc dẫn xuất của *PersistentObject* vào *PersistentSet*, lỗi sẽ xảy ra. Không ai mong muốn nhận được một exception khi gọi *Add*. Phương thức *Add* đã bị ảnh hưởng bởi dẫn xuất của *Set*, do đó việc thay đổi cấu trúc đã vi phạm LSP.

![image](https://user-images.githubusercontent.com/27339791/96059072-29525e00-0eb7-11eb-95ce-d4ffa8424e75.png)

## Một giải pháp không thỏa mãn LSP

Nhiều năm về trước, tác giả Robert giải quyết vấn đề bằng cách quy ước chứ không phải trong source code. Quy ước rằng *PersistentSet* và *PersistentObject* được ẩn đi trong chương trình, chỉ được dùng bởi một module đặc biệt.

Module đặc biệt này chịu trách nhiệm đọc và ghi tất cả các container từ persistent store. Khi cần ghi một container, nội dung của nó được đóng gói thành một dẫn xuất của *PersistentObject* và thêm vào *PersistentSet* sau đó được lưu vào một stream. Khi cần đọc một container từ stream, quy trình làm ngược lại. Một *PersistentSet* được đọc từ stream, và *PersistentObject* được lấy ra từ *PersistentSet* và chuyển thành một đối tượng thông thường rồi thêm vào *Set*.

Tại thời điểm đó, giải pháp trên tuy hạn chế nhưng là cách duy nhất tác giả nghĩ ra để ngăn chặn object *PersistentSet* xuất hiện trong interface của các phương thức muốn thêm vào các object non-persistent. Nó xóa bỏ sự phụ thuộc của phần còn lại của ứng dụng vào persistence.

Giải pháp này ổn không? Rõ ràng là không. Quy ước phải được truyền đạt cho từng developer. Trên thực tế, các developer chưa được học hoặc không đồng ý với quy ước này đã vi phạm nó.

## Một giải pháp theo LSP

Hiện tại tác giả đã giải quyết vấn đề như thế nào? Ta thấy rằng *PersistentSet* không có quan hệ IS-A với *Set* cho nên nó không phải là một dẫn xuất của *Set*. *Set* và *PersistentSet* có một số đặc tính chung và chỉ có phương thức *Add* là khó khăn cho LSP. Vì thế Robert đã tạo một phân cấp trong đó *Set* và *PersistantSet* là họ hàng cùng kế thừa một interface cho phép kiểm tra và duyệt các phần tử, trình bày trong hình 10-4. Điều này cho phép duyệt và kiểm tra các phần tử thuộc *PersistentSet*, mà không ảnh hưởng tới khả năng thêm các object không phải dẫn xuất từ *PersistentObject* vào *PersistentSet*.

![image](https://user-images.githubusercontent.com/27339791/96059248-9d8d0180-0eb7-11eb-986a-a36d67b41ba1.png)

## Sản xuất thay vì kế thừa

Một trường hợp thú vị và khó hiểu nữa của sự kế thừa là *đường thẳng* và *đoạn thẳng* với hai class tương ứng *Line* và *LineSegment*. Tham khảo hình 10-7 và 10-8. Thoạt nhìn thì hai class có vẻ phù hợp cho sự kế thừa. *LineSegment* cần dùng tất cả biến và phương thức được khai báo trong *Line*. Hơn nữa *LineSegment* thêm vào một phương thức mới của chính nó, *Length*, và ghi đè phương thức *IsOn*. Liệu nó có vi phạm LSP.

![image](https://user-images.githubusercontent.com/27339791/96804044-24058e00-1438-11eb-83fc-ca34c330329a.png)

Người dùng của *Line* có quyền mong muốn rằng tất cả các điểm thẳng hàng với nó đều nằm trên nó. Ví dụ điểm trả về bởi *YIntercept* là điểm mà đường thẳng giao với trục Y với x = 0. Vì nó nằm trên đường thẳng nên rõ ràng *IsOn(YIntercept) == true*. Trong nhiều thực thể của *LineSegment*, điều này là không đúng.

Tại sao đây lại là vấn đề? Có thể sống chung với lũ không? Đây là sự phán quyết. Có những trường hợp hiếm hoi khi mà chấp nhận một lỗ hổng tinh vi trong hành vi của tính đa hình hơn là cố gắng điều chỉnh thiết kế hoàn toàn thỏa mãn LSP. Sự chấp thuận thay vì sự hoàn hảo là sự đánh đổi của kỹ sư. *Một kỹ sư giỏi học được rằng sự thỏa hiệp đem lại lợi ích hơn là sự hoàn hảo. Tuy nhiên không nên dễ dàng thỏa hiệp đối với LSP*. Đảm bảo rằng class con luôn làm việc đúng với những phương thức sử dụng class cha là cách tốt nhất để đảm bảo tính linh hoạt. Một khi chối bỏ nó, chúng ta sẽ phải xem xét từng class con một.

> A good engineer learns when compromise is more profitable than perfection. However, comformance to LSP should not be surrendered lightly.
