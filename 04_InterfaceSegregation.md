# The Interface Segregation Principle (ISP)

> ***Các client không nên bị ép phụ thuộc vào những phương thức mà chúng không sử dụng.***

ISP giúp loại trừ khuyết điểm của các interface "đồ sộ" ("fat" interface). Ta nói các class sở hữu các interface không hề kết dính với nhau là các class có interface "đồ sộ". Nói cụ thể hơn, các interface của class đó có thể chia thành nhiều nhóm các phương thức. Mỗi nhóm phục vụ một tập client khác nhau. Khi đó một vài client sử dụng một nhóm các phương thức này, trong khi các client khác sử dụng nhóm phương thức khác.

ISP thừa nhận rằng có nhiều object yêu cầu các interface không liên quan nhau; tuy nhiên nó cũng chỉ ra rằng các client không nên nhìn chúng như là một class duy nhất. Thay vào đó các client nên sử dụng các abstract base class có các interface kết dính với nhau.

## Sự ô nhiễm Interface

Cùng xem xét một hệ thống an ninh ở Listing 12-1 có chứa object *Door* có thể được khóa, mở khóa và cho biết trạng thái của chúng là đang mở hay khóa. *Door* được code như một interface cho phép các client có thể triển khai *Door* interface theo cách riêng của chúng.

![image](https://user-images.githubusercontent.com/27339791/97456304-513bca00-196b-11eb-8b8c-a5bb159653fe.png)

Bây giờ xem xét một áp dụng là *TimedDoor*, cần phải kêu chuông báo động khi cánh cửa để ở chế độ mở quá lâu. Object *TimedDoor* giao tiếp với một object khác gọi là *Timer*. Xem Listing 12-2.

![image](https://user-images.githubusercontent.com/27339791/97456401-6ca6d500-196b-11eb-828c-a6ffcc4f0c6f.png)

Khi một object muốn khai báo một timeout, nó gọi phương thức *Register* của *Timer*. Tham số của phương thức *Register* là thời gian đếm ngược xảy ra timeout và một tham chiếu tới một object *TimeClient* mà phương thức *TimeOut* sẽ được gọi khi kết thúc timeout.

Làm thế nào chúng ta có thể làm cho class *TimerClient* giao tiếp với class *TimedDoor* để code trong *TimedDoor* có thể thông báo timeout? Figure 12-1 biểu diễn một giải pháp thông thường. Chúng ta thiết kế *Door*, tất nhiên kéo theo cả *TimedDoor*, kế thừa từ *TimerClient*. Điều này đảm bảo rằng *TimerClient* có thể đăng ký nó với *Timer* và nhận bản tin *TimeOut*.

![image](https://user-images.githubusercontent.com/27339791/97456615-9e1fa080-196b-11eb-830c-47b7bfdbe8fe.png)

Vấn đề của giải pháp này là class *Door* đã bị phụ thuộc vào *TimerClient*. Tuy nhiên, không phải tất cả các dẫn xuất của *Door* quan tâm tới thời gian. Hơn nữa lớp trừu tượng *Door* ban đầu không hề liên quan tới thời gian. Nếu một dẫn xuất không yêu cầu thời gian của *Door* được tạo mới, nó bị buộc phải định nghĩa phương thức suy biến cho phương thức *TimeOut*, tiềm ẩn nguy cơ vi phạm LSP. Mặt khác, những ứng dụng sử dụng các dẫn xuất này buộc phải import class *TimerClient* dù không sử dụng chúng. Rõ ràng đây là sự phức tạp không cần thiết và thừa thãi.

Đây là một ví dụ của sự ô nhiễm interface, một hội chứng phổ biến trong các ngôn ngữ kiểu cố định như Java, C++, C#. Interface của *Door* đã bị làm ô nhiễm bởi phương thức không bắt buộc chỉ vì lợi ích của một class con. Nếu mô hình này được giữ nguyên, thì mỗi khi một dẫn xuất cần một phương thức mới, phương thức đó sẽ được thêm vào base class. Cuối cùng chúng sẽ làm cho interface của base class ngày càng trở nên "đồ sộ".

Hơn nữa, mỗi khi một phương thức mới được thêm vào base class, phương thức đó phải được triển khai hoặc đặt default trong các lớp dẫn xuất. Như chúng ta đã tìm hiểu ở chương trước, giải pháp này có thể vi phạm LSP, dẫn tới hậu quả trong việc bảo trì và tái sử dụng hệ thống.

## Phân tách các Client nghĩa là phân tách các Interface

*Door* và *TimerClient* biểu diễn các interface được sử dụng bởi tệp client khác nhau. *Timer* dùng *TimerClient*, còn các class điều khiển cửa dùng *Door*.
Do các client là khác nhau, các interface cũng nên tách rời nhau. Tại sao? Bởi vì các client cố gắng tận dụng hết các nguồn lực có trong các interface của nó.

Khi chúng ta nghĩ về các nguồn lực có thể làm thay đổi phần mềm, chúng ta thường nghĩ về sự thay đổi của interface sẽ ảnh hưởng tới người dùng như thế nào. Ví dụ, chúng ta sẽ lo lắng về sự thay đổi đối với tất cả người dùng của *TimerClient* nếu interface của nó thay đổi. Tuy nhiên, có một nguồn lực hoạt động theo chiều ngược lại. Thỉnh thoảng, **người dùng buộc interface phải thay đổi**.

Lấy ví dụ, một vài người dùng của *Timer* muốn đăng ký nhiều hơn một yêu cầu timeout. Xem xét *TimedDoor*. Khi nó xác định rằng *Door* đang được mở, nó gửi một bản tin *Register* tới *Timer*, yêu cầu một timeout. Tuy nhiên, trước khi timeout đầu tiên hết hạn, cửa được đóng lại, sau đó mở ra một lần nữa. Khiến chúng ta phải đăng ký một yêu cầu timeout mới trước khi timeout cũ hết hiệu lực. Cuối cùng, yêu cầu timeout đầu tiên kết thúc, và phương thức *TimeOut* của *TimedDoor* được gọi. Xảy ra báo động giả.

Chúng ta có thể sửa chữa tình huống này bằng giải pháp nêu trong Listing 12-3. Chúng ta thêm vào một *timeOutId* duy nhất cho mỗi đăng ký timeout và lặp lại nó trong code của *TimeOut* của *TimerClient*. Điều này cho phép mỗi dẫn xuất của *TimerClient* biết được yêu cầu timeout nào đang được phản hồi lại.

Rõ ràng là sự thay đổi này sẽ ảnh hưởng tới tất cả các người dùng của *TimerClient*. Chúng ta chấp nhận nó bởi vì sự thiếu sót *timeOutId* cần phải sửa chữa. Tuy nhiên, thiết kế như trong Figure 12-1 kéo theo *Door*, và tất cả các client của *Door* bị ảnh hưởng bởi sửa chữa này. **Tại sao một lỗi trong *TimerClient* lại ảnh hưởng tới các client của dẫn xuất *Door* không yêu cầu chức năng thời gian**. Sự phụ thuộc lẫn nhau này sẽ khiến chúng ta phải trả giá. Khi một phần của chương trình thay đổi ảnh hưởng tới các phần khác dù chúng không hề liên quan, chi phí và hệ quả của sự thay đổi là không thể lường trước được.

![image](https://user-images.githubusercontent.com/27339791/97506773-4bb3a380-19ae-11eb-8df4-ece1024c6eaa.png)

## Nguyên lý tách biệt Interface (ISP)

> *Các client không nên bị ép phụ thuộc vào những phương thức mà chúng không sử dụng.*

Khi các client bị buộc phải phụ thuộc vào những phương thức chúng không sử dụng, chúng bị ảnh hưởng bởi sự thay đổi của những phương thức đó. Vô tình tạo ra một khớp nối giữa tất cả các client. Nói theo cách khác, khi một client phụ thuộc vào một class chứa các phương thức mà client không sử dụng nhưng các client khác lại dùng, client đó sẽ bị ảnh hưởng bởi sự thay đổi khi mà các client sử dụng class buộc class phải thay đổi. Chúng ta nên tránh sự kết nối không mong muốn nhiều nhất có thể, cho nên chúng ta cần tách biệt các interface.

## Class Interface và Object Interface

Suy nghĩ tiếp về *TimedDoor*. Đây là một object có hai interface riêng biệt được sử dụng bởi hai client khác nhau: *Timer* và người dùng của *Door*. Hai interface phải được triển khai trên cùng một object, do sự triển khai cả hai interface cùng điều khiển chung một dữ liệu, làm thế nào để thỏa mãn ISP? Làm thế nào để tách biệt hai interface khi chúng phải tồn tại cùng nhau?

Câu trả lời nằm ở thực tế rằng các client của một object không cần phải truy cập thông qua interface của object. Chúng có thể truy cập thông qua delegation hoặc thông qua một base class của object.

### Phân tách thông qua Delegation

Một giải pháp là tạo một object là dẫn xuất của *TimerClient* và delegate tới *TimedDoor* - Figure 12-2. Khi nó muốn đăng ký một timeout request với *Timer*, *TimedDoor* tạo một *DoorTimerAdapter* và đăng ký nó với *Timer*. Khi *Timer* gửi bản tin *TimeOut* tới *DoorTimerAdapter*, *DoorTimerAdapter* delegate bản tin tới *TimedDoor*.

![image](https://user-images.githubusercontent.com/27339791/106230343-de6c1480-6221-11eb-88bc-f0dc44197fbe.png)

Giải pháp này thỏa mãn ISP và tránh việc kết nối giữa *Door* client và *Timer*. Ngay cả khi nếu thay đổi *Timer* trình bày trong Listing 12-3 xảy ra, không người dùng nào của *Door* bị ảnh hưởng. Hơn nữa, *TimedDoor* không cần thiết phải có interface như là *TimerClient*. *DoorTimerAdapter* có thể "dịch" *TimerClient* vào trong *TimedDoor* interface. Do đó, đây là một giải pháp phổ biến (tham khảo Listing 12-4).

![image](https://user-images.githubusercontent.com/27339791/106230382-f2177b00-6221-11eb-8e3a-d7052a11e18f.png)

Tuy nhiên, giải pháp này cũng có chút không hoàn hảo. Nó liên quan tới việc tạo ra một object mới mỗi khi chúng ta đăng ký một timeout. Hơn nữa, delegation đòi hỏi runtime và bộ nhớ tuy nhỏ nhưng không phải bằng 0. Trong một vài lĩnh vực ứng dụng, ví dụ như embedded real-time control system, runtime và bộ nhớ hết sức quan trọng.

### Phân tách thông qua đa kế thừa

Figure 12-3 và Listing 12-5 biểu diễn cách thỏa mãn ISP bằng đa kế thừa. Trong hình mẫu này, *TimedDoor* kế thừa từ cả *Door* và *TimerClient*. Mặc dù các client của cả hai class có thể sử dụng *TimedDoor*, không ai phải phụ thuộc vào class *TimedDoor*. Do đó chúng sử dụng một object giống nhau thông qua interface riêng biệt.

![image](https://user-images.githubusercontent.com/27339791/106231231-cbf2da80-6223-11eb-9ced-5433771da133.png)

![image](https://user-images.githubusercontent.com/27339791/106231273-e5942200-6223-11eb-9b58-5d3ae46ed7ca.png)

Giải pháp này thường được tác giả sử dụng. Lý do duy nhất tác giả lựa chọn giải pháp ở Figure 12-2 thay vì Figure 12-3 là khi sự "biên dịch" xử lý bởi *DoorTimerAdapter* object là cần thiết hoặc cần phải xử lý biên dịch khác nhau ở các trường hợp khác nhau.

## Ví dụ giao diện người dùng của máy ATM

Cùng xem xét ví dụ tiêu biểu: Vấn đề của máy ATM. Giao diện người dùng của một máy ATM phải thật linh hoạt. Đầu ra có thể cần được dịch ra nhiều ngôn ngữ khác nhau, và trình chiếu trên một màn hình máy tính, màn hình chữ nổi (braille tablet), hoặc phát âm thanh ra loa qua một bộ tổng hợp giọng nói (Figure 12-4). Rõ ràng, sự linh hoạt có thể đạt được bằng cách tạo ra các abtract base class có các phương thức abstract cho tất cả các bản tin khác nhau cần biểu diễn trên giao diện.

![image](https://user-images.githubusercontent.com/27339791/106231626-b336f480-6224-11eb-95d9-54417682b9c1.png)

Một vấn đề nữa là mỗi giao dịch mà ATM thực hiện được đóng gói vào một dẫn xuất của class *Transaction*. Do đó, chúng ta cũng có các class là *DepositTransaction*, *WithdrawalTransaction*, *TransferTransaction*,... Mỗi class lại gọi các phương thức *UI*. Ví dụ để hỏi người dùng số tiền họ nạp vào, object *DepositTransaction* gọi phương thức *RequestDepositAmount()* của class *UI*. Tương tự, để hỏi người dùng số tiền họ muốn chuyển cho tài khoản khác, object *TransferTransaction* gọi phương thức *RequestTransferAmount()* của *UI*. Trình bày trong mô hình ở Figure 12-5.

![image](https://user-images.githubusercontent.com/27339791/106231976-859e7b00-6225-11eb-8beb-ed8b4af09e84.png)

Có thể thấy đây chính xác là trường hợp vi phạm ISP điển hình. Mỗi transaction sử dụng phương thức của class *UI* mà không class nào khác sử dụng đến. Dẫn tới khả năng thay đổi trong một dẫn xuất của *Transaction* sẽ kéo theo sự thay đổi của *UI*, cuối cùng ảnh hưởng ngược lại tới tất cả các dẫn xuất khác của *Transaction* cũng như các class khác phụ thuộc vào *UI* interface. Sự cứng nhắc và sự mỏng manh xuất hiện ở quanh đây.

Ví dụ, chúng ta thêm một *PayGasBillTransaction*, thì phải thêm một phương thức mới vào *UI* để xử lý bản tin độc nhất mà giao dịch này muốn hiển thị. Không may là *DepositTransaction*, *WithdrawTransaction*, và *TransferTransaction* đều phụ thuộc vào *UI* interface, chúng đều phải được biên dịch lại. Tồi tệ hơn, nếu những giao dịch này đã được triển khai như một thành phần của các hệ thống riêng biệt, toàn bộ sẽ phải triển khai lại từ đầu, mặc dù logic của chúng không hề thay đổi.

Sự kết nối không mong muốn này có thể được giải quyết bằng cách phân tách interface *UI* thành các interface riêng biệt, ví dụ như *DepositUI*, *WithdrawUI*, và *TransferUI*. Những interface này sau đó sẽ được đa kế thừa từ interface *UI*. Figure 12-6 và Listing 12-6 mô tả mô hình này.

![image](https://user-images.githubusercontent.com/27339791/106232436-9b607000-6226-11eb-9545-bff0a8f43a84.png)

![image](https://user-images.githubusercontent.com/27339791/106240713-542eab00-6237-11eb-8405-db9b87204511.png)
![image](https://user-images.githubusercontent.com/27339791/106240768-6f99b600-6237-11eb-9c01-a3ba6fd9e64d.png)

Khi một dẫn xuất mới của class *Transaction* được tạo, tương ứng một base class cho abstract interface *UI* được thêm vào, do đó interface *UI* và các dẫn xuất của nó phải được cập nhật. Tuy nhiên, những class này không được sử dụng rộng rãi. Thay vào đó, có lẽ chúng được sử dụng bởi hàm *main()* hoặc bất kỳ process nào bootup hệ thống và khởi động một instance *UI*. Vì vậy ảnh hưởng của việc thêm các base class mới của *UI* được hạn chế nhỏ nhất.

Xem xét kỹ hơn, Figure 12-6 cho thấy một vấn đề khi thỏa mãn ISP mà xuất hiện không rõ ràng lắm trong ví dụ về *TimedDoor*. Chú ý là mỗi một giao dịch có thông tin phiên bản cụ thể của *UI*. *DepositTransaction* phải biết về *DepositUI*, *WithdrawTransaction* phải biết về *WithdrawalUI*,... Trong Listing 12-6, tác giả đã xác định vấn đề này bằng cách buộc mỗi giao dịch phải được khởi tạo cùng với một instance *UI* cụ thể. Điều này cho phép tác giả sử dụng idiom trong Listing 12-7.

![image](https://user-images.githubusercontent.com/27339791/106243141-6ad70100-623b-11eb-86a4-b540ba678f10.png)

Cách xử lý này tuy tiện dụng nhưng đồng thời cũng buộc mỗi giao dịch chữa một thành phần tham chiếu tới interface *UI*. Trong C#, ai đó có thể cố gắng gộp tất cả các *UI* component vào trong một class duy nhất như trong Listing 12-8. Tuy nhiên, nó lại dẫn tới một hệ quả không tốt. Class *UIGlobals* phụ thuộc vào *DepositUI*, *WithdrawalUI*, và *TransferUI*. Nghĩa là một module muốn sử dụng interface *UI* sẽ phải phụ thuộc vào tất cả chúng, chính là tình huống mà ISP muốn tránh. Nếu một thay đổi làm thay đổi *UI* interface, tất cả các module sử dụng *UIGlobals* buộc phải biên dịch lại. Class *UIGlobals* đã kết hợp nhiều interface mà chúng ta đã vất vả để tách ra.

![image](https://user-images.githubusercontent.com/27339791/106243252-9fe35380-623b-11eb-8ccc-14902d536c3f.png)

Một hàm *g* cần phải truy cập vào *DepositUI* và *TransferUI*. Chúng ta muốn truyền vào tham số về user interface vào hàm này và có thể viết như sau:

> void g(DepositUI depositUI, TransferUI transferUI)

hoặc là:

> void g(UI ui)

Sự cám dỗ để viết theo cách thứ hai (đơn nguyên) là rất lớn. Chúng ta biết rằng ở cách thứ nhất (đa nguyên), cả hai tham số đều refer tới object giống nhau, hàm gọi có thể trông như sau:

> g(ui, ui)

Có thể hơi ngoan cố nhưng mà dạng đa nguyên thường được khuyên dùng hơn là dạng đơn nguyên. Dạng đơn nguyên buộc *g* phụ thuộc vào tất cả các interface chứa trong *UI*. Do đó, khi *WithdrawUI* thay đổi, *g* và tất cả client của *g* có thể bị ảnh hưởng. Điều này là ngoan cố hơn *g(ui, ui)*! Hơn nữa chúng ta cũng không thể đảm bảo rằng cả hai tham số của *g* sẽ luôn luôn refer tới object giống nhau. Trong tương lai, vì lí do nào đó mà interface object có thể được phân tách ra. Thực tế tất cả các interface được gom vào trong một object duy nhất là thông tin mà *g* không cần phải biết. Do đó, dạng đa nguyên được khuyên dùng cho những hàm tương tự.

Clients có thể được nhóm lại với nhau theo các phương thức dịch vụ chúng gọi. Việc gom nhóm cho phép phân tách interface theo từng nhóm thay vì cho từng client. Giúp giảm số lượng interface của dịch vụ và ngăn chặn việc dịch vụ phụ thuộc vào từng kiểu client.

Thỉnh thoảng các phương thức được gọi bởi các nhóm client khác nhau có thể trùng lặp. Nếu sự trùng lặp là nhỏ, interface cho các nhóm nên duy trì riêng biệt. Các phương thức chung nên được khai bảo trong tất cả các interface trùng lặp. Class server sẽ kế thừa những phương thức chung từ các interface nhưng chỉ triển khai chúng một lần.

Khi bảo trì các ứng dụng hướng đối tượng, các interface của class và component thường xuyên thay đổi. Thỉnh thoảng, những thay đổi này có ảnh hưởng lớn và buộc phải thực hiện lại việc biên dịch và triển khai một phần lớn của hệ thống. Ảnh hưởng này có thể được giảm nhẹ bằng cách thêm các interface mới vào các object sẵn có thay vì thay đổi interface cũ. Nếu client của interface cũ muốn truy cập các phương thức của interface mới, chúng có thể gọi object của interface mới, như trong Listing 12-9.

![image](https://user-images.githubusercontent.com/27339791/106244080-0157f200-623d-11eb-8762-9e4cd40e2f6a.png)

Như tất cả các nguyên lý khác, cần cẩn thận để không lạm dụng ISP. Một class có chứa hàng trăm interface khác nhau, một vài interface được phân tách bởi client, một vài interface khác được phân tách bởi phiên bản, rất đáng sợ.

## Kết luận

Các lớp "đồ sộ" gây ra các khớp nối kỳ lạ và độc hại giữa các client. Khi một client buộc class "đồ sộ" phải thay đổi, tất cả các client bị ảnh hưởng. Do đó client chỉ nên phụ thuộc vào các phương thức mà chúng gọi. Điều này có thể đạt được bằng cách phân chia interface của class "đồ sộ" ra thành nhiều interface đặc trưng của client. Mỗi interface đặc trưng chỉ khai báo các phương thức mà một hoặc nhóm client cụ thể gọi tới. Sau đó class "đồ sộ" có thể kế thừa tất cả các interface đặc trưng client và triển khai chúng. Nó giúp phá vỡ sự phụ thuộc của các client vào các phương thức mà chúng không sử dụng và cho phép các client đứng độc lập nhau.
