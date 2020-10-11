# Bốn tính chất cơ bản

Lập trình hướng đối tượng (OOP) có 4 tính chất cơ bản là:

+ Tính kế thừa (inheritance)
+ Tính trừu tượng (virtualization)
+ Tính đóng gói (encapsulation)
+ Tính đa hình (virtualization)

Mỗi tính chất là một góc nhìn riêng biệt, định nghĩa nên OOP.

Trước khi đi vào chi tiết, hãy xem xét 4 tính chất thể hiện ở một ví dụ cụ thể trong cuộc sống.

Hàng ngày mỗi chúng ta phải di chuyển từ nhà tới nơi học tập hay làm việc. Học sinh đi tới trường, công nhân đi tới nhà máy, đầu bếp đi tới nhà hàng, nhân viên văn phòng tới công ty... Nhìn chung mỗi trường hợp đều có 3 đối tượng xuất hiện là *con người, điểm xuất phát, và điểm đích*.

Chúng ta xây dựng một lớp trừu tượng (abstract class/interface) tên là *Movable* cho bài toán di chuyển này.

Nhiệm vụ (business) của lớp trừu tượng *Movable* là phục vụ việc di chuyển, nên nó định nghĩa một phương thức có tên là *move()* làm việc với 3 đối tượng đã liệt kê ở trên. Phương thức *move()* mới chỉ được định nghĩa chứ chưa chỉ ra cụ thể nó thực hiện di chuyển như thế nào. Đây là **tính trừu tượng**.

Việc gộp các tham số (*con người, điểm xuất phát, và điểm đích*) vào trong cùng một lớp. Chỉ chìa ra bên ngoài giao diện cần thiết  làm việc trên các tham số đó, ví dụ như phương thức thiết lập tham số *con người* là anh A hay chị B, hoặc phương thức *move()*. Các lớp bên ngoài không biết/không quan tâm các phương thức được thực hiện như thế nào. Đây là **tính đóng gói**.

Các phương tiện cụ thể như là ô tô, xe bus, xe máy, xe đạp, đi bộ... cùng mở rộng lớp trừu tượng *Movable*. Chúng chỉ ra cụ thể cách di chuyển (*move()*) như thế nào. Mỗi phương tiện lại thực hiện di chuyển theo một cách khác nhau: ví dụ lựa chọn đường đi như thế nào để tránh đường cấm, tránh đoạn đường hay xảy ra tắc nghẽn, hoặc xe bus thì phải đi theo đúng tuyến đường cố định... Sự thực hiện khác nhau của cùng một phương thức này chính là **tính đa hình**.

Đối với phương tiện xe bus, rõ ràng có nhiều tuyến xe với các lộ trình khác nhau, tự nhiên sinh ra các lớp đối tượng xe bus *"con"*, ví dụ bus_tuyến_09, bus_tuyến_23,... cùng có tính chất của xe bus nên nó kế thừa lớp xe bus có sẵn gọi là lớp *"cha"*. Mỗi lớp con kế thừa các tham số và phương thức của lớp cha mà không phải viết lại. Chúng chỉ cần định nghĩa lại một số phương thức, dễ thấy nhất là phương thức lấy lộ trình di chuyển. Đây là **tính kế thừa**.

Tóm lại:

* Tính trừu tượng: Định nghĩa phương thức *move()* giải quyết bài toán, nhưng chưa nói cách thực hiện như thế nào.

* Tính đóng gói: Gộp các tham số và các phương thức làm việc trên chúng vào cùng một class. Chỉ chìa ra các phương thức cần thiết và đơn giản nhất. Dấu di cách thực hiện bên trong.

* Tính đa hình: Lựa chọn phương tiện cụ thể thực hiện công việc di chuyển. Mỗi phương tiện lại có một cách thực hiện khác nhau đưa hành khách tới đích.

* Tính kế thừa: Các phương tiện cụ thể kế thừa các thuộc tính (properties) và tính chất (characteristics) có sẵn của lớp phương tiện cha. Có thể dùng lại, ghi đè, hoặc thêm nếu muốn.
