# The Interface Segregation Principle (ISP)

Nguyên lý này xử lý khuyết điểm của các interface "đồ sộ". Ta nói các class có các interface không kết dính với nhau là các interface "đồ sộ". Hay nói cách khác, các interface của class có thể phân chia thành nhiều nhóm chứa các phương thức. Mỗi nhóm phục vụ một tập client khác nhau. Do đó một vài client sử dụng một nhóm các phương thức, và các client khác sử dụng nhóm phương thức khác.

ISP thừa nhận răng có nhiều object yêu cầu các interface không kết dính; tuy nhiên nó chỉ ra rằng các client không nên nhìn chúng như là một class duy nhất. Thay vào đó các client nên sử dụng các abstract base class có các interface kết dính với nhau.

## Sự ô nhiễm Interface

Cùng xem một hệ thống an ninh ở ví dụ 12-1 có chứa object *Door* có thể được khóa và mở khóa và cho biết trạng thái của chúng là đang bị khóa hay đang mở. *Door* được code như một interface cho phép các client có thể triển khai *Door* interface theo cách riêng.

![image](https://user-images.githubusercontent.com/27339791/97456304-513bca00-196b-11eb-8b8c-a5bb159653fe.png)

Bây giờ xem xét một áp dụng, *TimedDoor*, cần phải kêu chuông báo động khi cánh cửa để ở chế độ mở quá lâu. Object *TimedDoor* giao tiếp với một object khác gọi là *Timer*. Xem ví dụ 12-2.

![image](https://user-images.githubusercontent.com/27339791/97456401-6ca6d500-196b-11eb-828c-a6ffcc4f0c6f.png)

Khi một object muốn khai báo một timeout, nó gọi phương thức *Register* của *Timer*. Tham số của phương thức *Register* là thời gian đếm ngược xảy ra timeout và một tham chiếu tới một object *TimeClient* mà phương thức *TimeOut* sẽ được gọi khi kết thúc timeout.

Làm thế nào chúng ta có thể làm cho class *TimerClient* giao tiếp với class *TimedDoor* để code trong *TimedDoor* có thể thông báo timeout? Hình 12-1 biểu diễn một giải pháp thông thường. Chúng ta thiết kế *Door*, tất nhiên kéo theo cả *TimedDoor*, kế thừa từ *TimerClient*. Điều này đảm bảo rằng *TimerClient* có thể đăng ký nó với *Timer* và nhận bản tin *TimeOut*.

![image](https://user-images.githubusercontent.com/27339791/97456615-9e1fa080-196b-11eb-830c-47b7bfdbe8fe.png)

Vấn đề của giải pháp này là class *Door* bị phụ thuộc vào *TimerClient*. Không phải tất cả các dẫn xuất của *Door* cần quan tâm đến thời gian. Thay vào đó lớp trừu tượng *Door* ban đầu không liên quan tới thời gian. Nếu một dẫn xuất không yêu cầu thời gian của *Door* được tạo, nó phải cung cấp phương thức suy biến cho phương thức *TimeOut*, khả năng cao sẽ vi phạm LSP. Hơn nữa, những ứng dụng sử dụng các dẫn xuất này sẽ phải import class *TimerClient*, mặc dù không sử dụng chúng. Rõ ràng đây là sự phức tạp không cần thiết và thừa thãi.

Đây là một ví dụ của sự ô nhiễm interface, một hội chứng phổ biến trong các ngôn ngữ kiểu cố định như Java, C++, C#. Interface của *Door* đã bị làm ô nhiễm bởi phương thức không bắt buộc chỉ vì lợi ích của một class con. Nếu mô hình này được giữ nguyên, mỗi khi một dẫn xuất cần một phương thức mới, phương thức đó sẽ được thêm vào base class. Cuối cùng chúng sẽ làm cho interface của base class ngày càng trở nên "đồ sộ".

Hơn nữa, mỗi khi một phương thức mới được thêm vào base class, phương thức đó phải được triển khai hoặc đặt mặc định trong các lớp dẫn xuất. Như chúng ta đã tìm hiểu ở chương trước, giải pháp này có thể vi phạm LSP, dẫn tới hậu quả trong việc bảo trì và tái sử dụng hệ thống.

## Phân tách các Client nghĩa là phân tách các Interface

*Door* và *TimerClient* biểu diễn các interface được sử dụng bởi các client khác nhau. *Timer* dùng *TimerClient*, còn các class điều khiển cửa dùng *Door*.
