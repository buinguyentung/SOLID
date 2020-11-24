# The Interface Segregation Principle (ISP)

ISP giúp loại trừ khuyết điểm của các interface "đồ sộ". Ta nói các class mà triển khai nhiều interface không hề liên quan đến nhau là các class có giao diện "đồ sộ". Hay nói cách khác, các interface của class có thể được phân chia thành nhiều nhóm khác nhau. Mỗi nhóm chứa các phương thức khác nhau, phục vụ một tập client khác nhau. Khi đó một vài client sử dụng một nhóm các phương thức này, trong khi các client khác sử dụng nhóm phương thức khác.

ISP thừa nhận rằng có nhiều object yêu cầu các interface không liên quan nhau; tuy nhiên nó cũng chỉ ra rằng các client không nên nhìn chúng như là một class duy nhất. Thay vào đó các client nên sử dụng các abstract base class có các interface kết dính với nhau.

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
Do các client là khác nhau, các interface cũng nên tách rời nhau. Tại sao? Bởi vì các client cố gắng tận dụng hết các nguồn lực có trong các interface của nó.

Khi chúng ta nghĩ về các nguồn lực có thể làm thay đổi phần mềm, chúng ta thường nghĩ về sự thay đổi của interface sẽ ảnh hưởng tới người dùng như thế nào. Ví dụ, chúng ta sẽ lo lắng về sự thay đổi đối với tất cả người dùng của *TimerClient* nếu interface của nó thay đổi. Tuy nhiên, có một nguồn lực hoạt động theo chiều ngược lại. Thỉnh thoảng, **người dùng buộc interface phải thay đổi**.

Lấy ví dụ, một vài người dùng của *Timer* muốn đăng ký nhiều hơn một yêu cầu timeout. Xem xét *TimedDoor*. Khi nó xác định rằng *Door* đang được mở, nó gửi một bản tin *Register* tới *Timer*, yêu cầu một timeout. Tuy nhiên, trước khi timeout đó hết hạn, cửa được đóng lại, sau đó mở ra một lần nữa. Khiến chúng ta phải đăng ký một yêu cầu timeout mới trước khi timeout cũ hết hiệu lực. Cuối cùng, yêu cầu timeout đầu tiên kết thúc, và phương thức *TimeOut* của *TimedDoor* được gọi. Xảy ra báo động giả.

Chúng ta có thể sửa chữa tình huống này bằng giải pháp nêu trong ví dụ 12-3. Chúng ta thêm vào một *timeOutId* duy nhất cho mỗi đăng ký timeout và lặp lại nó trong code của *TimeOut* của *TimerClient*. Điều này cho phép mỗi dẫn xuất của *TimerClient* biết được yêu cầu timeout nào đang được phản hồi lại.

Rõ ràng là sự thay đổi này sẽ ảnh hưởng tới tất cả các người dùng của *TimerClient*. Chúng ta chấp nhận nó bởi vì sự thiếu sót *timeOutId* cần phải sửa chữa. Tuy nhiên, thiết kế trong hình 12-1 kéo theo *Door*, và tất cả các client của *Door* bị ảnh hưởng. **Tại sao một lỗi trong *TimerClient* lại ảnh hưởng tới các client của dẫn xuất *Door* không yêu cầu chức năng thời gian**. Sự phụ thuộc lẫn nhau này sẽ khiến chúng ta phải trả giá. Khi một phần của chương trình thay đổi ảnh hưởng tới các phần khác dù chúng không hề liên quan, chi phí và hệ quả của sự thay đổi là không thể lường trước được.

![image](https://user-images.githubusercontent.com/27339791/97506773-4bb3a380-19ae-11eb-8df4-ece1024c6eaa.png)

## Nguyên lý tách biệt Interface

> Các client không nên bị ép phụ thuộc vào những phương thức mà chúng không sử dụng.

Khi các client bị buộc phải phụ thuộc vào những phương thức chúng không sử dụng, chúng bị ảnh hưởng bởi sự thay đổi của những phương thức đó. Vô tình tạo ra một khớp nối giữa tất cả các client. Nói theo cách khác, khi một client phụ thuộc vào một class chứa các phương thức mà client không sử dụng nhưng các client khác lại dùng, client đó sẽ bị ảnh hưởng bởi sự thay đổi mà các client khác buộc class phải thay đổi. Chúng ta nên tránh khớp nối nhiều nhất có thể, cho nên chúng ta cần tách biệt các interface.

## Class Interface và Object Interface

## Kết luận

Các lớp "đồ sộ" gây ra các khớp nối kỳ lạ và độc hại giữa các client. Khi một client buộc class "đồ sộ" phải thay đổi, tất cả các client bị ảnh hưởng. Do đó client chỉ nên phụ thuộc vào các phương thức mà chúng gọi. Điều này có thể đạt được bằng cách phân chia interface của class "đồ sộ" ra thành nhiều interface đặc trưng của client. Mỗi interface đặc trưng chỉ khai báo các phương thức mà một hoặc nhóm client cụ thể gọi tới. Sau đó class "đồ sộ" có thể kế thừa tất cả các interface đặc trưng client và triển khai chúng. Nó giúp phá vỡ sự phụ thuộc của các client vào các phương thức mà chúng không sử dụng và cho phép các client đứng độc lập nhau.
