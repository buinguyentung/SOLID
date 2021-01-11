# The Dependency-Inversion Principle (DIP)

Nguyên lý đảo ngược sự phụ thuộc được định nghĩa như sau:

> A. Các module cấp cao không nên phụ thuộc vào module cấp thấp. Cả hai nên phụ thuộc vào abstraction.

> B. Abstraction không nên phụ thuộc vào chi tiết/module cụ thể. Chi tiết/module cụ thể nên phụ thuộc vào abstraction.

Chúng ta phải giảm bớt sự ràng buộc giữa các module. Sự liên kết chặt chẽ (*tightly coupled*) là khi mà các module/class bị ràng buộc vào một module/class khác. Khi ta sửa đổi hoặc thay thế một module có thể dẫn tới sự sụp đổ của toàn bộ ứng dụng. Thay vào đó, chúng ta sử dụng abstraction hay interface để giảm tối đa sự liên kết chặt chẽ trong hệ thống. Nguyên lý DIP giúp giải quyết vấn đề này.

![image](https://user-images.githubusercontent.com/27339791/104143832-a4aea780-53f3-11eb-82aa-ba7ce67e7c5c.png)

Một ví dụ bên ngoài đời sống, hệ thống điện tòa nhà không nên phụ thuộc vào ổ cắm của máy sấy tóc. Nếu quan niệm rằng ổ cắm đi theo máy sấy tóc là cố định, do đó hệ thống điện phải chìa ra một phích cắm tương thích với ổ cắm của máy sấy tóc, thì khi chúng ta mua sắm các thiết bị điện khác, nhiều trường hợp sẽ xảy ra sự không tương thích với hệ thống điện.

![image](https://user-images.githubusercontent.com/27339791/104144231-ff94ce80-53f4-11eb-8920-d22beb6133cf.png)

Đảo ngược sự phụ thuộc, hệ thống điện (module cấp cao) cần định nghĩa một chuẩn ổ cắm (abstraction). Máy sấy tóc hay thiết bị điện khác (module cấp thấp) muốn sử dụng điện phải tuân thủ chuẩn ổ cắm mới cắm được vào ổ cắm.

![image](https://user-images.githubusercontent.com/27339791/104145368-500e2b00-53f9-11eb-8326-93cf06cc4bfe.png)

Quay trở lại với bài viết. Trong nhiều năm, rất nhiều người hỏi tác giả tại sao lại dùng từ "*inversion*" (đảo ngược) ở tên nguyên lý. Lý do là các phương pháp phát triển phần mềm truyền thống, kiểu như phân tích cấu trúc và thiết kế, thường xây dựng các kiến trúc phần mềm trong đó các module cấp cao phụ thuộc vào các module cấp thấp và luật lệ phụ thuộc vào cụ thể. Sự thực là một trong những mục tiêu của các phương pháp đó là định nghĩa một phân cấp mô tả cách mà các module bậc cao gọi tới các module bậc thấp. Thiết kế ban đầu của chương trình *Copy* ở minh họa 7.1 là một ví dụ tiêu biểu cho sự phân cấp. Cấu trúc phụ thuộc của một chương trình hướng đối tượng thiết kế tốt là "*đảo ngược*" cấu trúc phụ thuộc được thiết kế bởi các phương pháp truyền thống.

![image](https://user-images.githubusercontent.com/27339791/104146867-d711d200-53fe-11eb-8245-b370d72d1551.png)

Xem xét hàm ý của module cấp cao phụ thuộc vào module cấp thấp. Module cấp cao chứa các luật quyết định quan trọng và mô hình kinh doanh của một ứng dụng. Những module này giúp xác định danh tính của ứng dụng. Khi mà những module bậc cao phụ thuộc vào các module bậc thấp, sự thay đổi ở module bậc thấp có thể ảnh hưởng trực tiếp tới module bậc cao và buộc chúng phải thay đổi.

Sự thay đổi này là vô lý. **Các module bậc cao thiết lập luật mới là module ảnh hưởng tới các module bậc thấp**. Các module chứa mô hình kinh doanh phải được ưu tiên hơn, và độc lập khỏi các module triển khai cụ thể. Các module bậc cao không nên phụ thuộc vào các module bậc thấp trong bất kỳ hoàn cảnh nào.

Hơn nữa, chúng ta muốn sử dụng lại các module cấp cao thiết lập luật lệ. Chúng ta đã thành thạo việc sử dụng các module cấp thấp dưới dạng các thư viện chương trình. Khi module cấp cao phụ thuộc vào module cấp thấp, việc sử dụng lại các module cấp cao dưới ngữ cảnh khác sẽ rất khó khăn. Ngược lại, **khi module cấp cao độc lập với module cấp thấp, các module cấp cao có thể được tái sử dụng dễ dàng**. Nguyên lý này là cốt lõi của thiết kế framework.

## Layering - Phân lớp

Theo Booch, "*tất cả các kiến trúc hướng đối tượng tốt đều thiết kế các lớp rõ ràng, mỗi lớp cung cấp một set các dịch vụ thông qua một interface được định nghĩa rõ ràng và kiểm soát tốt*". Một cách hiểu đơn giản của mệnh đề này có thể hướng dẫn người thiết kế tạo ra một cấu trúc tương tự như minh họa 11-1.

![image](https://user-images.githubusercontent.com/27339791/104151106-af2a6a80-540e-11eb-87f9-03544dd28939.png)

Trong đó, lớp cấp cao *Policy* sử dụng một lớp cấp thấp *Mechanism*, và lớp *Mechanism* sử dụng một lớp cấp chi tiết *Utility*. Thiết kế này có vẻ phù hợp, tuy nhiên nó lại có một điểm không tốt là lớp *Policy* nhạy cảm với những sự thay đổi của lớp *Utility*. **Sự phụ thuộc có tính chất bắc cầu**. Lớp *Policy* phụ thuộc vào lớp *Mechanism*, lớp *Mechanism* phụ thuộc vào lớp *Utility*. Do đó, lớp *Policy* phụ thuộc bắc cầu vào lớp *Utility*. Điểm này thực sự không tốt chút nào.

Minh họa 11-2 đưa ra một thiết kế phù hợp hơn. **Mỗi lớp ở cấp cao hơn khai báo một abstract interface cho các dịch vụ cần thiết. Các lớp cấp thấp hơn sẽ triển khai từ các abstract interface đó**. Mỗi lớp cấp cao hơn lại sử dụng cấp dưới liền kề nó thông qua abstract interface. Do đó, lớp cấp cao không phụ thuộc vào lớp cấp dưới. Thay vào đó, lớp cấp dưới phụ thuộc vào abstract interface của dịch vụ được khai báo trong lớp trên. Không chỉ sự phụ thuộc bắc cầu bị phá vỡ, mà cả sự phụ thuộc trực tiếp của lớp *Policy* vào lớp *Mechanism*.

![image](https://user-images.githubusercontent.com/27339791/104152081-2f51cf80-5411-11eb-9977-61c885d067c7.png)

## Ownership Inversion (Đảo ngược quyền sở hữu)

TBU


## Tham khảo
[1] Agile Principles, Patterns, and Practices Chương 11
