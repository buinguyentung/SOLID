# The Open Closed Principle (OCP)

> Các thành phần của phần mềm (class, module, function,...) nên mở với sự mở rộng nhưng đóng với sự thay đổi.

Khi sự thay đổi một chương trình mà kéo theo phải thay đổi các module phụ thuộc, thì có vẻ như thiết kế đó đang bị cứng nhắc (rigidity). OCP giúp chúng ta thiết kế hoặc tái cấu trúc lại hệ thống để khi có sự thay đổi thì ta chỉ cần thêm các dòng code mới đáp ứng yêu cầu, mà không phải viết lại những dòng code cũ đang chạy trơn tru.

## OCP là gì?

Các module thỏa mãn OCP có hai tính chất:

1. <b>Chúng mở với sự mở rộng</b>. Có nghĩa là các hành vi của module có thể được mở rộng. Khi các yêu cầu của một ứng dụng thay đổi, chúng ta có thể mở rộng module bằng các hành vi mới thỏa mãn yêu cầu.

2. <b>Chúng đóng với sự thay đổi</b>. Mở rộng hành vi của module không làm thay đổi code của module đó. Đồng thời các file binary đã được biên dịch (sử dụng các API của module) như là .DLL hoặc .EXE cũng không phải biên dịch lại.

Hai tính chất này thoạt nhìn có vẻ đối lập nhau. Thông thường muốn mở rộng hành vi của một module thì chúng ta phải sửa code của module đó. Một module nếu không thể thay đổi thì được coi là có các hành vi cố định. Làm thể nào mà ta có thể thay đổi hành vi của module khi mà không viết lại code của nó?

Câu trả lời là <b>nguyên lý trừu tượng – Abstraction</b>. Trong bất kỳ một ngôn ngữ lập trình hướng đối tượng nào (OOPL), chúng ta hoàn toàn có thể tạo ra các khối trừu tượng biểu diễn một nhóm các hành vi. Các khối trừu tượng có thể là các interface hoặc lớp trừu tượng cơ sở (abstract base class), còn các hành vi sẽ được định nghĩa ở các lớp dẫn xuất (derived class) kế thừa các lớp cơ sở.
Như vậy, một module sẽ đóng với sự thay đổi khi nó phụ thuộc vào một lớp trừu tượng cố định. Trong khi các hành vi của module có thể được mở rộng ở các lởp con thừa kế lớp trừu tượng cơ sở.

Hình 9-1 biểu diễn một thiết kế không thỏa mãn OCP. *Client* và *Server* là các lớp cụ thể. Class *Client* sử dụng trực tiếp class *Server*. Khi ta muốn một object *Client* dùng một object *Server* khác, class *Client* phải sửa sang tên của class *Server* mới.

![image](https://user-images.githubusercontent.com/27339791/94212958-d01e8c80-feff-11ea-8816-7da44be7d864.png)

Hình 9-2 biểu diễn một thiết kế thỏa mãn OCP sử dụng *STRATEGY* pattern.
*ClientInterface* là một lớp trừu tượng chứa các phương thức trừu tượng. Class *Server* triển khai (implement) *ClientInterface* và định nghĩa cụ thể các phương thức trừu tượng theo cách của nó. Còn class *Client* sử dụng khối trừu tượng *ClientInterface* chứ không trực tiếp sử dụng class *Server* nữa.

![image](https://user-images.githubusercontent.com/27339791/94213084-2ee40600-ff00-11ea-8a67-44c9aea17fae.png)

Cần để ý là các object *Client* sẽ sử dụng các object của lớp *Server*. Nếu chúng ta muốn một object *Client* sử dụng một class *Server* khác, một lớp dẫn xuất của class *ClientInterface* có thể được tạo ra. Class *Client* không phải thay đổi gì.

Client có một danh sách các công viêc cần phải thực hiện, ta định nghĩa chúng thành các phương thức trừu tượng trong *ClientInterface*. Các lớp con của *ClientInterface* tự do định nghĩa cụ thể các phương thức theo cách chúng muốn. Do đó, các hành vi của Client có thể được mở rộng bằng cách tạo thêm các class con của ClientInterface.

Tại sao tác giả lại đặt tên là **ClientInterface**, chứ không phải là **AbstractServer**?

> “Abstract classes are more closely associated to their clients than to the classes that implement them.”

Nghĩa là khi chúng ta định nghĩa một abstract class thì chúng ta biết nó phải làm những cái gì, phục vụ mục đích gì và hướng tới Client nào. Abstract class không biết các sub class phát triển nó ra sao.

Hình 9-3 biểu diễn mộ thiết kế khách sử dụng *TEMPLATE METHOD* pattern.

![image](https://user-images.githubusercontent.com/27339791/94213327-b3cf1f80-ff00-11ea-81d3-f2b2ca715691.png)

Class *Policy* chứa các phương thức public nhằm triển khai một chính sách, tương tự các phương thức của Client trong hình 9-2. Tuy nhiên thay vì dùng một Interface, ở ví dụ này chúng ta đưa các phương thức trừu tượng vào trong Class *Policy*. Trong Java ta gọi chúng là **phương thức trừu tượng (abstract method)**, còn C++ gọi chúng là **hàm ảo thuần túy (pure virtual function)**. Những phương thức trừu tượng này sẽ phải được định nghĩa cụ thể bên trong các lớp kế thừa class Policy như class Implementation. Do vậy, các hành vi của class Policy có thể được mở rộng và thay đổi bằng cách tạo thêm các lớp dẫn xuất của class Policy.

Hai pattern trên là các cách phổ biến nhất để thỏa mãn OCP. Chúng phân tách các chức năng đặc trưng ra khỏi cách thực thi cụ thể các chức năng đó.

## Ứng dụng Shape

Ví dụ Shape thường được trình bày trong các cuốn sách về thiết kế hướng đối tượng làm ví dụ cho nguyên lý đa hình. Lần này chúng ta sẽ dùng nó để làm sáng tỏ OCP.

Ứng dụng Shape có chức năng vẽ hình tròn và hình vuông lên GUI. Có một danh sách các hình tròn và hình vuông sắp xếp sẵn theo thứ tự, và chương trình phải vẽ các hình theo thứ tự đó.

## Thiết kế Shape vi phạm OCP

Ví dụ 9-1 trình bày một cách để giải quyết vấn đề mà không thỏa mãn OCP. Ta có một tập các cấu trúc dữ liệu có thành phần đầu tiên giống nhau (*ShapeType*) giúp phân biệt cấu trúc dữ liệu đó là của hình tròn hay hình vuông. Hàm *DrawAllShapes* duyệt lần lượt từng phần tử của mảng con trỏ tới các cẩu trúc dữ liệu, kiểm tra kiểu rồi gọi hàm vẽ phù hợp, là *DrawCircle* hoặc *DrawSquare*.

![image](https://user-images.githubusercontent.com/27339791/94213504-28a25980-ff01-11ea-8d82-cff98ac97012.png)
![image](https://user-images.githubusercontent.com/27339791/94213571-56879e00-ff01-11ea-91cc-e43d71ec5010.png)

Thiết kế này vi phạm OCP vì nó không thể đóng với sự thay đổi khi chúng ta thêm bất kỳ kiểu hình mới nào – chẳng hạn hình tam giác, ta sẽ phải sửa hàm *DrawAllShapes*.

* Thứ nhất: Trên thực tế câu lệnh switch/case sẽ được lặp lại không chỉ ở hàm DrawAllShape, mà còn ở nhiều chức năng khác của chương trình như di chuyển hình, kéo hình, xóa hình,… Việc thêm một hình mới vào chương trình không đơn giản như tưởng tượng.

* Thứ hai: Hơn nữa, không phải hàm nào cũng có thể dùng câu lệnh switch hay if/else như hàm *DrawAllShapes*. Ở một số trường hợp rắc rồi, ta không thể dùng các câu lệnh rẽ nhánh để tách biệt xử lý cho hình tròn hay hình vuông. Do đó, việc tìm kiếm – đọc hiểu – sửa code cho loại hình mới sẽ không khác gì một thảm họa.

* Thứ ba: Ta cũng phải suy nghĩ về các sự thay đổi khác. Ta phải thêm thành viên mới vào enum *ShapeType*. Tất cả các hình được khai báo trong enum này nên ta phải biên dịch lại chúng. Đồng thời ta cũng phải biên dịch lại các module phụ thuộc vào Shape.

[Chú ý] Trong C/C++, thay đổi enum có thể thay đổi size của biến được dùng để lưu enum. Vì thế chúng ta cũng phải để ý đến việc biên dịch lại các cấu trúc khai báo shape.

Vậy chúng ta không chỉ thay đổi code của tất cả các câu lệnh rẽ nhánh switch/case hoặc if/else, mà còn phải biên dịch lại file binary của tất cả các module sử dụng cấu trúc dữ liệu Shape. Một hành động thêm loại hình mới đơn giản, mà kéo theo một chuỗi hành động phức tạp là hệ quả của một thiết kế tồi .

## Thiết kế thõa mãn OCP

Hình 9-2 trình bày một đoạn code cho giải pháp thỏa mãn OCP. Chúng ta có một abstract class tên là *Shape*. Abstract class này chỉ có một hàm ảo (abstract method) là *Draw*. Lớp *Circle* và *Square* là các lớp dẫn xuất của *Shape*.

![image](https://user-images.githubusercontent.com/27339791/94213795-fe04d080-ff01-11ea-9bcb-a8ca5abab18a.png)

Khi chúng ta muốn vẽ một loại hình mới, thì chỉ cần thêm một lớp dẫn xuất của *Shape*. Hàm *DrawAllShapes* không phải thay đổi, nên nó thỏa mãn OCP. Hành vi của *DrawAllShapes* đã được mở rộng mà không phải thay đổi. Hơn nữa việc thêm class *Triangle* sẽ không ảnh hưởng gì tới các module được viết ở trên, tất nhiên một số phần khác của hệ thống phải thay đổi để làm việc với class *Triangle*, nhưng không phải là đoạn code ở hình 9-2.

Trên thực thế, class *Shape* có nhiều phương thức khác, việc thêm hình mới sẽ vẫn đơn giản là tạo một lớp dẫn xuất của class *Shape* và triển khai tất cả các phương thức, không cần thiết phải tìm khắp ứng dụng những phương thức cần thay đổi. Giải pháp này không hề dễ vỡ (fragile).

Giải pháp này cũng không hề cứng nhắc. Không tồn tại source module phải thay đổi, không module binary phải biên dịch lại ngoài một ngoại lệ. Chính là module tạo instance của lớp dẫn xuất mới của class *Shape*. Thông thường, đó là module *main*, hoặc một hàm nào đó gọi bởi *main*, hoặc trong phương thức của một object tạo bởi *main* (Những object này được gọi là *factory* – Tham khảo Factory pattern sau).

Cuối cùng, giải pháp này không cố định (immobile). Hàm *DrawAllShape* có thể được sử dụng lại bởi bất kỳ ứng dụng nào mà không cần khai báo thêm *Circle* hoặc *Square*. Có thể thấy giải pháp này đã tránh được các tính chất của một thiết kế tồi.

Chương trình đã thỏa mãn OCP. Nó thay đổi bằng cách viết thêm code mới mà không thay đổi code hiện tại. Thay đổi duy nhất là thêm module mới và module main khởi tạo object của module đó.

Tuy nhiên, nếu chúng ta quyết định rằng các hình tròn phải được vẽ trước các hình Vuông. Hàm *DrawAllShape* sẽ phải thay đổi, ít ra là duyệt list tìm vẽ các hình tròn, sau đó cho hình vuông. *DrawAllShape* không hề đóng với sự thay đổi này.

Vậy giải pháp là gì?
