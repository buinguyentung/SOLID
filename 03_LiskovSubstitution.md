# The Liskov Substitution Principle (LSP)

Open/Closed Principle (OCP) chủ yếu dựa trên tính trừu tượng và tính đa hình của OOP. Trong các ngôn ngữ lập trình kiểu dữ liệu tĩnh (statically typed) như C# hay Java, chúng ta sử dụng kế thừa (*inheritance*) để thể hiện chúng. Ta có thể tạo ra các lớp dẫn xuất định nghĩa cụ thể hành vi của các phương thức ảo được khai báo trong lớp cơ sở.

Vậy quy tắc thiết kế nào quản lý việc kế thừa? Quy định các tính chất của một phân cấp kế thừa tốt nhất? Các lỗi chúng ta hay gặp khiến việc phân cấp kế thừa làm thiết kế không còn thỏa mãn OCP?
Câu trả lời nằm ở LSP.

> **Kiểu con phải có thể thay thế được cho kiểu cơ sở.** <br>
Subtypes must be substitutable for their base types.

Năm 1988, Barbara Liskov viết nguyên lý này như sau:

> Chúng ta cần một mô hình đảm bảo tính thay thế: Nếu với mỗi object o1 thuộc kiểu S có một object o2 thuộc kiểu T mà tất cả các chương trình P định nghĩa với T, thì hành vi của P không thay đổi khi o1 thay thế cho o2 và S là kiểu con của T.

Tầm quan trọng của LSP thể hiện rõ ràng nếu ta nhìn thấy hậu quả của việc vi phạm nó. Giả sử ta có hàm f có một tham số là tham chiếu tới lớp cơ sở B. Giả sử ta truyền vào f dưới vỏ bọc B là một dẫn xuất D của B làm f hoạt động sai. Khi đó D vi phạm LSP. Rõ ràng, D rất mỏng manh khi f tồn tại.

Tác giả của hàm f sẽ cố gắng tạo một số test case cho D, để đảm bảo f hoạt động chính xác khi truyền D cho nó. Các test case này lại vi phạm OCP, bởi vì f không đóng với các lớp dẫn xuất của B.

## Ví dụ trong đời sống

Ta lên kế hoạch bán hàng online và cần tìm hiểu dịch vụ chuyển phát nhanh bưu kiện tới khách hàng. Dịch vụ shipper (*abstraction*) ta quan tâm cần phải cung cấp đẩy đủ các phương tiện vận chuyển phù hợp với tất cả kích cỡ hàng hóa và yêu cầu về thời gian vận chuyển như xe máy, ô tô, tàu hỏa,… Đồng thời họ phải hỗ trợ các loại thanh toán bằng tiền mặt, chuyển khoản hoặc quẹt thẻ. Ta quyết định lựa chọn công ty A (*instance*) vì theo quảng cáo họ đáp ứng được đẩy đủ các yêu cầu trên.

Hợp đồng được ký kết. Ta hài lòng cho tới khi đơn hàng đầu tiên được chuyển đi thì phát hiện ra rằng: công ty A sử dụng máy POS kiểu cũ nên chỉ hỗ trợ quẹt thẻ từ, chứ không quẹt được thẻ chip mới (loại đang được ngân hàng chuyển đổi). Như vậy đối tượng A đã không giống với mô tả abstraction ban đầu.

# Vi phạm LSP

## Một ví dụ đơn giản

Việc vi phạm LSP thưởng gây ra hậu quả là phải kiểm tra kiểu lúc runtime dẫn tới vi phạm OCP. Thông thường câu lệnh điều kiện if/else được sử dụng để xác định kiểu của object và gọi các hành vi tương ứng với kiểu đó. Ví dụ 10-1.

![image](https://user-images.githubusercontent.com/27339791/94753462-81b63580-03b8-11eb-99e5-22d30c9dcd70.png)

Rõ ràng hàm *DrawShape* vi phạm OCP vì nó phải biết tất cả các dẫn xuất của class *Shape*, và phải thay đổi mỗi khi dẫn xuất mới được thêm vào.
