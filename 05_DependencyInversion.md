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

Figure 11-2 minh họa một thiết kế phù hợp hơn. **Mỗi lớp ở cấp cao hơn khai báo một abstract interface cho các dịch vụ cần thiết. Các lớp cấp thấp hơn sẽ triển khai từ các abstract interface đó**. Mỗi lớp cấp cao hơn lại sử dụng cấp dưới liền kề nó thông qua abstract interface. Do đó, lớp cấp cao không phụ thuộc vào lớp cấp dưới. Thay vào đó, lớp cấp dưới phụ thuộc vào abstract interface của dịch vụ được khai báo trong lớp trên. Không chỉ sự phụ thuộc bắc cầu bị phá vỡ, mà cả sự phụ thuộc trực tiếp của lớp *Policy* vào lớp *Mechanism*.

![image](https://user-images.githubusercontent.com/27339791/104152081-2f51cf80-5411-11eb-9977-61c885d067c7.png)

## Ownership Inversion (Đảo ngược quyền sở hữu)

Sự đảo ngược ở đây không chỉ là sự phụ thuộc mà còn là quyền sở hữu interface. Chúng ta thường cho rằng các thư viện công cụ sở hữu interface của riêng chúng. Tuy nhiên khi áp dụng DIP, chúng ta thấy rằng **các client có xu hướng sở hữu abstract interface và các server của chúng xuất phát từ đó**.

Note: Xem lại một chút định nghĩa Client và Server ở chương 9 (OCP). Client ám chỉ các lớp cấp cao, Server ám chỉ các lớp cấp thấp hơn.
![image](https://user-images.githubusercontent.com/27339791/94213084-2ee40600-ff00-11ea-8a67-44c9aea17fae.png)

Điều này thỉnh thoảng được gọi là nguyên tắc Hollywood: "*Đừng gọi cho chúng tôi, chúng tôi sẽ gọi bạn.*" Các module cấp thấp triển khai các interface được khai báo và gọi bởi các module cấp cap hơn.

Sử dụng sự đảo ngược quyền sở hữu này, layer *Policy* không bị ảnh hưởng bởi sự thay đổi của layer *Mechanism* và *Utility*. Hơn nữa layer *Policy* có thể được tái sử dụng trong các ngữ cảnh có các module cấp thấp thỏa mãn *Policy Service Interface*. Do đó, bằng cách đảo ngược sự phụ thuộc, chúng ta tạo ra một cấu trúc linh hoạt hơn, bền hơn, và có tính di động.

Trong ngữ cảnh này, quyền sở hữu đơn giản có nghĩa là quyền sở hữu các interface được phân phối giữa các client, không phải giữa các server triển khai chúng. **Interface nằm trong cùng package hoặc thư viện với client**. Điều này buộc các thư viện hoặc package của server phụ thuộc vào thư viện hoặc package của client.

Dĩ nhiên, nhiều lúc chúng ta không muốn server phụ thuộc vào client. Điều này đặc biệt đúng khi có nhiều client nhưng chỉ có một server. Trong trường hợp đó, các client phải đồng ý sử dụng service interface và publish nó trong một package riêng biệt.

## Dependence on Abstractions (Sự phụ thuộc vào Abstractions)

Nói một cách tham lam về DIP: "Phụ thuộc vào abstractions". Chúng ta không nên phụ thuộc vào một lớp cụ thể, tất cả các quan hệ trong một chương trình nên thông qua một abstract class hoặc một interface.

> Không biến nào nên giữ tham chiếu tới một class cụ thể.

> Không class nào nên kế thừa từ một class cụ thể.

> Không phương thức nào nên override một phương thức đã được triển khai trong bất kỳ base class nào của nó.

Chắc chắn là tính tham lam này bị vi phạm ít nhất một lần trong mọi chương trình. Ai đó phải tạo các instance của các class cụ thể, và module đó sẽ phụ thuộc vào chúng. Hơn nữa, dường như không có lý do gì để tuân theo tính tham lam này cho các class cụ thể nhưng *nonvolatile* (nghĩa là class không bị thay đổi "bất thường"). Nếu một class cụ thể không thay đổi nhiều, và không có các dẫn xuất tương tự được tạo ra, không có nhược điểm mấy khi phụ thuộc vào class đó.

Ví dụ, trong hầu hết hệ thống, class định nghĩa một string là class cụ thể. Trong C# là class *string*. Class này là nonvolatile. Nó không thay đổi thường xuyên. Vì vậy, không có hại gì khi phụ thuộc nó.

Tuy nhiên, hầu hết các class cụ thể mà chúng ta viết ra trong chương trình là *volatile* (nghĩa là có khả năng thay đổi cao). Chúng ta không muốn phụ thuộc trực tiếp vào các class này. Sự dễ thay đổi của class có thể được vượt qua bằng cách giữ chúng đằng sau các abstract interface.

Giải pháp này là không hoàn chỉnh. Có những khi interface của một volatile class phải thay đổi, và sự thay đổi này phải được lan truyền trong abstract interface đại diện cho class đó. Những sự thay đổi như vậy phá vỡ sự cô lập của abstract interface.

Đó là lý do cho rằng sự tham lam có phần ngây thơ. Mặt khác, nếu chúng ta dành thời gian xem xét kỹ hơn các client module hoặc layer khai báo các service interface mà chúng cần, thì interface sẽ chỉ phải thay đổi khi yêu cầu của client thay đổi. Những sự thay đổi trong các class triển khai abstract interface sẽ không ảnh hưởng tới client.

## Một ví dụ DIP đơn giản

DIP có thể được áp dụng ở bất cứ chỗ nào mà một class gửi một message tới một class khác. Ví dụ trường hợp object *Button* và *Lamp*.

Object *Button* nhận tác động từ bên ngoài. Khi nhận được message *Poll*, object *Button* xác định người dùng đã ấn nó hay chưa. Không quan trọng cơ chế xác nhận là gì, có thể là một button icon trên GUI, có thể là một button vật lý mà người dùng dùng tay để ấn, thậm chí có thể là một cảm biến chuyển động của hệ thống bảo vệ nhà. Object *Button* xác định là người dùng đã bật hay tắt nó.

Object *Lamp* ảnh hưởng tới môi trường xung quanh. Khi nhận một message *TurnOn*, object *Lamp* bật đèn lên. Khi nhận message *TurnOff*, object *Lamp* tắt đèn. Cơ chế vật lý là không quan trọng.

Làm thế nào chúng ta thiết kế một hệ thống mà object *Button* điều khiển object *Lamp*. Figure 11-3 mô tả một mô hình khá ngây thơ. Object *Button* nhận message *Poll*, xác định button đã được ấn hay chưa, và đơn giản gửi message *TurnOn* hoặc *TurnOff* tới *Lamp*.

![image](https://user-images.githubusercontent.com/27339791/104173559-8c14b080-5438-11eb-9e39-256739570ef4.png)

Tại sao nó lại ngây thơ? Xem xét code C# triển khai mô hình ở Listing 11-1. Để ý là class *Button* phụ thuộc trực tiếp vào class *Lamp*. Sự phụ thuộc này nghĩa là *Button* sẽ bị ảnh hưởng bởi các sự thay đổi của *Lamp*. Hơn nữa sẽ không thể tái sử dụng *Button* để điều khiển một object *Motor*. Trong mô hình này, object *Button* chỉ có thể điều khiển object *Lamp* mà thôi.

![image](https://user-images.githubusercontent.com/27339791/104174425-165d1480-5439-11eb-9d9c-eae9c5af13cb.png)

Giải pháp này vi phạm DIP. Luật cấp cao của ứng dụng không được tách rời với sự triển khai ở cấp thấp. Abstraction không được tách rời khỏi chi tiết. Không có sự chia tách này, luật cấp cao tự động phù thuộc vào module cấp thấp, và abstraction tự động phụ thuộc vào chi tiết.

## Tìm Abstraction

Luật cấp cao là gì? **Đó là abstraction nằm dưới một ứng dụng, là sự thực bất biến khi các chi tiết bị thay đổi**. Trong ví dụ Button/Lamp, abstraction bên dưới là xác định thao tác on/off của người dùng và chuyển hành vi đó tới object mục tiêu. Cơ chế để xác định thao tác on/off người dùng là gì? Không liên quan. Object mục tiêu là gì? Không liên quan. Những chi tiết đó không ảnh hưởng tới abstraction.

Mô hình trong Figure 11-3 có thể được cải tiến bằng cách đảo ngược sự phụ thuộc vào object *Lamp*. Trong Figure 11-4, chúng ta thấy rằng bây giờ *Button* giữ một tham chiếu tới *ButtonServer*, cung cấp interface cho phép *Button* có thể bật/tắt một cái gì đó. *Lamp* triển khai interface *ButtonServer*. Do đó bây giờ *Lamp* sẽ phụ thuộc thay vì bị phụ thuộc vào.

![image](https://user-images.githubusercontent.com/27339791/104176993-a3ed3400-543a-11eb-875a-34f1278883bc.png)

Thiết kế trong Figure 11-4 cho phép một *Button* điều khiển bất kỳ thiết bị nào mong muốn triển khai interface *ButtonServer*. Đó là tính linh hoạt. Nó cũng có nghĩa là object *Button* sẽ có thể điều khiển các object mà chưa được phát minh ra.

Tuy nhiên, **giải pháp này đưa ra một ràng buộc rằng bất kỳ object nào cần điều khiển bởi một *Button* thì phải triển khai interface *ButtonServer***. Điều không may bởi những object này có thể cũng muốn được điều khiển bởi object *Switch* hoặc các object khác ngoài *Button*.

Bằng cách đảo ngược sự phụ thuộc và làm cho *Lamp* phụ thuộc thay vì bị phụ thuộc vào, chúng ta làm cho cho *Lamp* phụ thuộc vào một chi tiết khác: *Button*. Phải không?

*Lamp* chắc chắn là phụ thuộc vào *ButtonServer*, nhưng *ButtonServer* không phụ thuộc vào *Button*. Bất kỳ object nào biết cách điều khiển interface *ButtonServer* đều có thể điều khiển được *Lamp*. Do đó, sự phụ thuộc chỉ là ở cái tên. Chúng ta có thể làm rõ hơn bằng cách thay đổi tên của *ButtonServer* thành cụ thể hơn, ví dụ như *SwitchableDevice*. Chúng ta cũng đảm bảo rằng *Button* và *SwitchableDevice* nằm ở trong các thư viện khác nhau, nhờ vậy khi sử dụng *SwitchableDevice* không ám chỉ rằng sẽ sử dụng *Button*.

Trong trường hợp này, không ai sở hữu interface *ButtonServer* cả. Chúng ta có một tình huống khá thú vị là interface có thể được sử dụng bởi nhiều client khác nhau, và được triển khai bởi nhiều server khác nhau. Do đó, interface phải đứng riêng lẻ, không thuộc vào một trong hai nhóm trên. Trong C#, chúng ta đặt cho nó một namespace và library riêng biệt.

## The Furnance Example (Ví dụ về lò đốt)

Giả sử rằng phần mềm phải điều khiển bộ điều chỉnh của một lò đốt. Phần mềm có thể đọc nhiệt độ hiện tại từ một kênh I/O và điều khiển lò đốt bật hoặc tắt bằng cách gửi các lệnh tới một kênh I/O khác. Cấu trúc của thuật toán có thể giống như Listing 11-2.

![image](https://user-images.githubusercontent.com/27339791/104540081-5e568400-5651-11eb-8da7-ba7f758fae14.png)

Ý định cấp cao của thuật toán là khá rõ ràng, nhưng mà code chứa rất nhiều chi tiếp cấp thấp và không thể tái sử dụng để điều khiển phần cứng khác.

Số lượng code là nhỏ nên chúng ta có thể không thấy rõ sự mất mát. Nhưng kể cả như vậy, thật xấu hổ khi có một thuật toán mà không thể tái sử dụng. Chúng ta đảo ngược sự phụ thuộc như Figure 11-5.

![image](https://user-images.githubusercontent.com/27339791/104540575-4fbc9c80-5652-11eb-84f2-0f84e89e2516.png)

Có thể thấy hàm *Regulate* sử dụng hai tham số đều là interface. Interface *Thermometer* có thể đọc (read), interface *Heater* có thể vận hành hoặc ngừng vận hành (engage và disengage). Đây là tất cả những gì mà thuật toán *Regulate* cần tới. Bây giờ nó có thể được viết như Listing 11-3.

![image](https://user-images.githubusercontent.com/27339791/104541176-9bbc1100-5653-11eb-9b40-af39136029a3.png)

Chúng ta đã đảo ngược sự phụ thuộc của luật điều khiển cấp cao vào bất kỳ chi tiết nào như nhiệt kế hay là lò đốt. Nhờ vậy, thuật toán có thể dễ dàng tái sử dụng.

## Kết luận

Thủ tục lập trình truyền thống thường tạo ra một cấu trúc phụ thuộc ở đó luật lệ phụ thuộc vào chi tiết. Điều này không may dẫn tới tình trạng các luật lệ dễ bị tổn thương khi các chi tiết thay đổi. OOP đảo ngược cấu trúc phụ thuộc bằng cách thiết kế các chi tiết và luật lệ phụ thuộc vào abstraction, và service interfaces thường được sở hữu bởi các client.

Hơn nữa, sự đảo ngược phụ thuộc là đặc trưng của một thiết kế hướng đối tượng tốt. Không quan trọng ngôn ngữ lập trình là gì. Nếu các sự phụ thuộc được đảo ngược, nó là một thiết kế hướng đối tượng. Nếu sự phụ thuộc không được đảo ngược, nó là một thiết kế thủ công.

Nguyên lý đảo ngược sự phụ thuộc là cơ chế cấp thấp cơ bản đằng sau nhiều lợi ích mà hướng đối tượng mang lại. Nó cho phép ứng dụng tạo nên các framework có thể tái sử dụng. Nó cũng rất quan trọng khi xây dựng code có thể thay đổi nhanh chóng. Nhờ abstraction và chi tiết tách biệt nhau, việc bảo trì code trở nên dễ dàng hơn.

Note: Sau khi đọc xong về nguyên lý **Dependency Inversion**, chúng ta nên tìm hiểu về các thuật ngữ **Inversion of Control** và **Dependency Injection** để tránh nhầm lẫn giữa các khái niệm.

> **Inversion of Control**: một nguyên lý thiết kế phần mềm (một số người cho rằng đó là một pattern). Nó được sử dụng để đảo ngược các loại điều khiển trong thiết kế hướng đối tượng nhằm mục tiêu nới lỏng các liên kết chặt chẽ (loose coupling).

> **Dependency Inversion**: một nguyên lý thiết kế hướng đối tượng.

> **Dependency Injection**: một kỹ thuật để triển khai DIP.

![image](https://user-images.githubusercontent.com/27339791/104543952-3ec35980-5659-11eb-85dd-7c3c8d55625b.png)

## Tham khảo
[1] Agile Principles, Patterns, and Practices Chương 11

[2] https://allaravel.com/blog/nguyen-ly-dependency-inversion-bi-quyet-tao-ra-he-thong-mo
