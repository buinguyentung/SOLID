# The Single Responsibility Principle (SRP)

* <i>Một class chỉ nên có duy nhất một lý do để thay đổi.</i>

Hay nói cách khác, một class chỉ nên chịu trách nhiệm về một nhiệm vụ cụ thể nào đó mà thôi. 

Ví dụ, một nhân viên văn phòng vừa phải làm việc kiếm thu nhập, vừa phải chăm sóc con nhỏ, mà lại phải nấu nướng, giặt giũ...  Khả năng cao là anh ta sẽ sớm bị stress vì ôm đồm quá nhiều việc.

Tại sao lại phải chia mỗi chức năng thành một class riêng biệt? Bởi vì mỗi chức năng là một lý do để thay đổi class. Ví dụ, khi khách hàng thay đổi yêu cầu, yêu cầu đó sẽ được phản ánh thông qua sự thay đổi chức năng của class đã được thiết kế. Như vậy một class sẽ phải có nhiều hơn một lý do phải thay đổi nếu nó đang xử lý nhiều chức năng.

Hơn nữa, các chức năng của class có thể trở nên ảnh hưởng lẫn nhau (coupled). Thay dổi một chức năng có thể làm mất khả năng xử lý chức năng còn lại của class.

Ví dụ, class Rectangle có hai phương thức. Một phương thức vẽ hình chữ nhật lên màn hình, một phương thức tính diện tích hình chữ nhật đó.

![image](https://user-images.githubusercontent.com/27339791/93567022-29466780-f9b8-11ea-9474-4810cb1984f9.png)
 
Có hai ứng dụng khác nhau sử dụng class Rectangle. Một ứng dụng tính toán hình học, dùng class Rectangle để tính toán nhưng không bao giờ vẽ hình chữ nhật lên màn hình. Ứng dụng còn lại là đồ họa, vẽ hình chữ nhật lên màn hình và thỉnh thoảng có thể tính toán một chút.

Thiết kế này vi phạm SRP. Class Rectangle có hai chức năng cung cấp hàm tính toán của hình chữ nhật và chức năng hiển thị hình chữ nhật lên GUI.

Vấn đề với thiết kế này là, chúng ta cũng phải đóng gói GUI vào trong ứng dụng tính toán hình học (mặc dù nó không dùng tới). Nếu có thay đổi trong ứng dụng đồ họa dẫn tới class Rectangle phải thay đổi liên quan đến GUI, rất có thể chúng ta cũng phải rebuild, retest, và redeploy ứng dụng tính toán - . 

Một thiết kế tốt hơn là chia hai chức năng thành hai class riêng biệt. Tách phần tính toán thành class GeometricRectangle. Phần đồ họa sẽ không ảnh hưởng tới tính toán như sau.

![image](https://user-images.githubusercontent.com/27339791/93567337-ad98ea80-f9b8-11ea-9bf2-41a04c418d74.png)
 

## Định nghĩa một chức năng (responsibility)
Với SRP, một chức năng là một lý do để thay đổi. Nếu ta có thể có nhiều hơn một động cơ để thay đổi một class, class đó đang có nhiều hơn một chức năng.

Ví dụ: interface Modem. 
Thoạt nhìn chúng ta thấy interface Modem định nghĩa 4 phương thức là chức năng của Modem rất hợp lý.

![image](https://user-images.githubusercontent.com/27339791/93567471-e3d66a00-f9b8-11ea-90bf-96f4852e6d00.png)
 
Tuy nhiên, interface này lại bao gồm hai chức năng:
-	Quản lý kết nối: phương thức dial() và hangup().
-	Trao đổi dữ liệu: phương thức send() và recv()

Chúng ta có nên tách hai chức năng này ra? Điều này phụ thuộc vào sự thay đổi của ứng dụng. Giả sử ứng dụng thay đổi mà ảnh hưởng tới chức năng kết nối, dẫn tới các class gọi các phương thức trao đổi dữ liệu phải recompile và redeploy đồng thời. Trường hợp này, chúng ta có thể chia các chức năng ra như sau:

![image](https://user-images.githubusercontent.com/27339791/93567572-0b2d3700-f9b9-11ea-9ac6-42e1d6eaa7a4.png)
 
Ngược lại, nếu ứng dụng thay đổi mà hai chức năng này không ảnh hưởng đến nhau thì không cần thiết phải tách ra. Điều này sẽ giúp giảm bớt độ phức tạp của thiết kế.

* Rút ra: <i>Mỗi một chức năng là một trục thay đổi (axis of change)</i>, là tâm điểm cho sự tồn tại của class. Tuy nhiên, <i>một trục thay đổi là một trục thay đổi chỉ khi sự thay đổi xảy ra</i>. Sẽ là không khôn ngoan nếu áp dụng bất kỳ một nguyên lý nào nếu không có lý do phải áp dụng chúng.

## Phân tách các chức năng phụ thuộc lẫn nhau

Ở ví dụ 8-3, hai chức năng quản lý kết nối và trao đổi dữ liệu vẫn được ghép cặp với nhau trong class ModemImplementation. Sự tồn tại của ModelImplementation là không mong muốn nhưng lại cần thiết. Trên thực tế, phụ thuộc vào phần cứng hoặc hệ điều hành, chúng ta phải ghép nhiều chức năng vào với nhau. Bằng cách chia tách thành các interface, ta giảm bớt sự ảnh hưởng lẫn nhau của các chức năng trong một ứng dụng.

Trong ví dụ này, chỉ có hàm main mới biết sự tồn tại của class ModelImplementation, không class nào phải phụ thuộc vào nó. Các class khác chỉ cần quan tâm tới các interface Connection và DataChannel phục vụ mục đích của chúng.

## Tính cố định
Hình 8-4 mô tả một ví dụ vi phạm SRP thường gặp. Class Employee có cùng lúc hai chức năng là hoạt động nghiệp vụ và điều khiển các hệ thống cố định. Hai chức năng này không bao giờ nên kết hợp với nhau bởi vì hoạt động nghiệp vụ thì thường xuyên có sự thay đổi, trong khi việc điều khiển hệ thống cố định rất ít khi thay đổi, hoặc chỉ thay đổi vì một lý do xác đáng nào đó.

![image](https://user-images.githubusercontent.com/27339791/93567693-3dd72f80-f9b9-11ea-9f70-65b3c44f360a.png)


## Kết luận:
Nguyên lý Single-Responsibility là một trong những nguyên lý đơn giản nhất nhưng khó để áp dụng một cách chính xác. Chúng ta thường có xu hướng vô thức gộp nhiều chức năng vào một class làm cho nó trở nên đa nhiệm. Xác định và phân tách các chức năng là nhiệm vụ quan trọng của software design.

