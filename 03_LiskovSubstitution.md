# The Liskov Substitution Principle (LSP)

Open/Closed Principle (OCP) chủ yếu dựa trên tính trừu tượng và tính đa hình của OOP. Trong các ngôn ngữ lập trình kiểu dữ liệu tĩnh (statically typed) như C# hay Java, chúng ta sử dụng kế thừa (*inheritance*) để thể hiện chúng. Ta có thể tạo ra các lớp dẫn xuất định nghĩa cụ thể hành vi của các phương thức ảo được khai báo trong lớp cơ sở.

Vậy quy tắc thiết kế nào quản lý việc kế thừa? Quy định các tính chất của một phân cấp kế thừa tốt nhất? Các lỗi chúng ta hay gặp khiến việc phân cấp kế thừa làm thiết kế không còn thỏa mãn OCP?
Câu trả lời nằm ở LSP.

> **Các kiểu con phải có thể thay thế được cho các kiểu cha.** <br>
Subtypes must be substitutable for their base types.

Năm 1988, Barbara Liskov viết nguyên lý này như sau:

> Chúng ta cần một mô hình đảm bảo tính thay thế: Nếu với mỗi object o1 thuộc kiểu S có một object o2 thuộc kiểu T mà tất cả các chương trình P định nghĩa với T, thì hành vi của P không thay đổi khi o1 thay thế cho o2 và S là kiểu con của T.

Tầm quan trọng của LSP thể hiện rõ ràng nếu ta nhìn thấy hậu quả của việc vi phạm nó. Giả sử ta có hàm f có một tham số là tham chiếu tới lớp cơ sở B. Giả sử ta truyền vào f dưới vỏ bọc B là một dẫn xuất D của B làm f hoạt động sai. Khi đó D vi phạm LSP. Rõ ràng, D rất mỏng manh khi f tồn tại.

Tác giả của hàm f sẽ cố gắng tạo một số test case cho D, để đảm bảo f hoạt động chính xác khi truyền D cho nó. Các test case này lại vi phạm OCP, bởi vì f không đóng với các lớp dẫn xuất của B.

## Ví dụ trong đời sống

Ta lên kế hoạch bán hàng online và cần tìm hiểu dịch vụ chuyển phát nhanh bưu kiện tới khách hàng. Dịch vụ shipper (*abstraction*) ta yêu cầu phải cung cấp đẩy đủ các phương tiện vận chuyển phù hợp với tất cả kích cỡ hàng hóa như xe máy, ô tô, tàu hỏa,… và linh hoạt thời gian vận chuyển (chuyển phát nhanh, tiêu chuẩn). Đồng thời họ phải hỗ trợ các loại thanh toán bằng tiền mặt, chuyển khoản hoặc quẹt thẻ. Ta quyết định lựa chọn công ty A (*an instance*) vì theo quảng cáo họ đáp ứng được đẩy đủ các yêu cầu trên.

Hợp đồng được ký kết. Những đơn hàng được chuyển phát nhanh chóng, cho tới khi thanh toán thì chúng ta phát hiện ra rằng: công ty A sử dụng máy POS kiểu cũ nên chỉ hỗ trợ quẹt thẻ từ, chứ không quẹt được thẻ chip mới. Như vậy đối tượng A đã không giống với mô tả abstraction ban đầu.

# Vi phạm LSP

## Một ví dụ đơn giản

Việc vi phạm LSP thưởng gây ra hậu quả là phải kiểm tra kiểu lúc runtime dẫn tới vi phạm OCP. Thông thường câu lệnh điều kiện if/else được sử dụng để xác định kiểu của object và gọi các hành vi tương ứng với kiểu đó. Ví dụ 10-1.

![image](https://user-images.githubusercontent.com/27339791/94753462-81b63580-03b8-11eb-99e5-22d30c9dcd70.png)

Rõ ràng hàm *DrawShape* vi phạm OCP vì nó phải biết tất cả các dẫn xuất của class *Shape*, và phải thay đổi mỗi khi dẫn xuất mới được thêm vào.

Engineer Joe sau khi học về lập trình hướng đối tượng đã kết luận rằng tính đa hình là quá phức tạp cho trường hợp này. Do đó anh ta định nghĩa class *Shape* mà không có phương thức nào. Các class *Square* và *Circle* dẫn xuất từ *Shape* có phương thức *Draw()*, nhưng không phải là ghi đè từ class *Shape*. Vì *Circle* và *Square* không thể thay thế cho *Shape*, *DrawShape* phải xác định loại hình cần vẽ, sau đó gọi phương thức *Draw* tương ứng.

Vấn đề là *Square* và *Circle* không thể thay thế cho *Shape* vi phạm LSP. Vi phạm này dẫn đến phương thức *DrawShape* vi phạm OCP.

> Sự vi phạm LSP là hệ quả của sự vi phạm OCP.

## Một vi phạm khôn khéo hơn

Cùng tham khảo một chương trình sử dụng class *Rectangle* ví dụ 10-2.

![image](https://user-images.githubusercontent.com/27339791/95398597-686d3600-0930-11eb-8941-4dfb59ad0ff5.png)

Chương trình hoạt động tốt và được triển khai ở nhiều nơi. Cho đến một ngày, khách hàng yêu cầu thêm các hình vuông (squares).

Thông thường **kế thừa là quan hệ IS-A**. Nghĩa là ta nói một loại object mới có quan hệ IS-A với một loại object cũ thì class của object mới nên là lớp dẫn xuất từ class của object cũ.

Rõ ràng hình vuông là một hình chữ nhật nên chúng ta thiết kế class *Square* kế thừa từ class *Rectangle*.

![image](https://user-images.githubusercontent.com/27339791/95399123-a61e8e80-0931-11eb-903f-5c8dd756b36a.png)

Sử dụng quan hệ IS-A thường được coi là kỹ thuật cơ bản của hướng đối tượng, được sử dụng nhiều nhưng hiếm khi được định nghĩa. Hình vuông là hình chữ nhật nên class *Square* là dẫn xuất từ class *Rectangle*. Suy nghĩ này có thể dẫn đến vấn đề nghiêm trọng mà ta chưa thấy khi thiết kế.

Điểm đầu tiên là một hình vuông không cần phải có cả hai thuộc tính *Height* và *Width* mà nó kế thừa từ class *Rectangle*. Sự phí phạm này sẽ trở thành vấn đề khi chương trình chúng ta phải tạo hàng trăm ngàn hình vuông.

Nếu chúng ta bỏ qua sự quan tâm tới memory (giả sử thôi^^) thì chúng ta lại gặp ngay vấn đề khác. Hình vuông có chiều dài và chiều rộng bằng nhau nên các setter cho hai thuộc tính *Height* và *Width* là không chính xác. Chúng ta lại vá bằng cách ghi đè như sau:

![image](https://user-images.githubusercontent.com/27339791/95399854-5b9e1180-0933-11eb-80bd-6ba70969e330.png)

Bây giờ khi chiểu rộng của object *Square* được thay đổi thì chiều dài của nó cũng đổi theo và ngược lại. Về mặt toán học, object *Square* giữ được tính chất của hình vuông.

```C
Square s = new Square();
s.SetWidth(1); // Height cũng bằng 1.
s.setHeight(2); // Width cũng bằng 2.
```

Hãy xem xét hàm sau:

```C
void f(Rectangle r) {
    r.setWidth(32); // gọi Rectangle.setWidth
}
```

Nếu chúng ta truyền một tham chiếu của object *Square* vào hàm này, object *Square* sẽ đổ vỡ vì chiều dài sẽ không thay đổi. Đây là sự vi phạm LSP. Hàm f không hoạt động với các dẫn xuất của tham số. Lý do là *Height* và *Width* không được khai báo virtual trong *Rectangle* nên nó không có tính đa hình.

Chúng ta có thể sửa bằng cách chuyển các phương thức setter thành virtual. Tuy nhiên việc thêm lớp dẫn xuất mới khiến class cơ sở phải thay đổi là vi phạm OCP. Chúng ta phải giải trình lý do sửa phương thức thành virtual là không lường trước được sự tồn tại của class *Square*.

Giả sử khách hàng chấp nhận thay đổi code:

![image](https://user-images.githubusercontent.com/27339791/95925338-c643c700-0de3-11eb-80fe-45826a3dc926.png)

Bây giờ cả *Square* và *Rectangle* đều hoạt động tốt (bỏ qua vấn đề về bộ nhớ). Hình vuông và hình chữ nhật được đảm bảo về mặt toán học. Ta hoàn toàn có thể truyền một hình vuông vào hàm có tham số là hình chữ nhật. Thiết kế đã thực sự tốt chưa?

## Vấn đề thực sự

Xem xét hàm g.

```C
void g(Rectangle r) {
    r.Width = 5;
    r.Height = 4;
    if (r.Area() != 20)
        throw new Exception("Bad area!");
}
```

Hàm g gọi các thuộc tính *Width* và *Height* của *Rectangle*. Hàm này hoạt động tốt với *Rectangle* nhưng sẽ tạo Exception khi ta truyền vào *Square*. Tác giả của hàm g tin rằng *khi thay đổi chiều rộng của hình chữ nhật thì chiều dài của nó không thay đổi*.

Hàm g trở nên dễ vỡ với phân cấp *Square/Rectangle*. *Square* không thể thay thế cho *Rectangle* trong các trường hợp tương tự. Quan hệ giữa chúng vi phạm LSP.

Nếu chúng ta đổ lỗi cho hàm g rằng tác giả không thể giả định chiều dài và chiều rộng là độc lập nhau. Điều này là không hợp lý. Hiển nhiên một hình chữ nhật có chiều dài và chiều rộng độc lập nhau. Chính tác giả của class *Square* đã vi phạm điều hiển nhiên này. Chúng ta không vi phạm tính chất của *Square* nhưng khi kế thừa *Square* từ *Rectangle*, ta đã vi phạm tính chất của *Rectangle*.

## Sự công nhận không thuộc về bản chất

LSP đưa ra một kết luận quan trọng: *Một mô hình, xem xét một cách độc lập, không thể được đánh giá đầy đủ.* Công nhận một mô hình chỉ có thể được thực hiện theo cái nhìn của các *"client"* sử dụng mô hình đó. Ví dụ khi chúng ta kiểm tra phiên bản cuối của lớp *Square* và *Rectangle* độc lập, chúng ta thấy rằng chúng là nhất quán và hợp lệ. Nhưng khi chúng ta nhìn bằng cái nhìn của một programmer đưa ra những giả thiết hợp lý về class cơ sở, mô hình bị phá vỡ.

Khi đánh giá một thiết kế cụ thể là đúng đắn, chúng ta không thể xem xét mô hình một cách độc lập. Ta phải xem xét dựa trên những giả thiết hợp lý từ những người sử dụng thiết kế đó. Thông thường, những giả thiết được yêu cầu trong khi viết unit test cho class cơ sở (test-driven development).

Ngược lại, nếu chúng ta cố gắng dự đoán tất cả các giả thiết, ta có thể gây ra sự phức tạp không cần thiết (*needless complexity*) cho thiết kế. Do đó, tương tự như các nguyên lý khác, chúng ta chỉ nên tập trung vào các vi phạm LSP rõ ràng nhất làm thiết kế trở nên dễ vỡ.

## IS-A là về hành vi

Vậy chuyện gì đã xảy ra? Tại sao mô hình *Square* và *Rectangle* lại không tốt? Liệu đó có phải là một quan hệ IS-A?

Từ góc nhìn của tác giả hàm *g* thì không. Object *Square* không phải là một object *Rectangle*. Tại sao? Vì hành vi của một object *Square* không giống với hành vi của một object *Rectangle*. Hay nói cách khác hình vuông không phải là hình chữ nhật. LSP làm rõ trong thiết kế hướng đối tượng (OOD), quan hệ IS-A thuộc về *hành vi* mà có thể đưa ra giả thiết một cách hợp lý từ người sử dụng.

## Thiết kế dựa trên những giao kèo

Nhiều người sẽ cảm thấy không thoải mái khi nghĩ về các hành vi được "*giả thiết hợp lý*". Làm thế nào để biết người sử dụng mong muốn gì? Có một kỹ thuật chỉ rõ các giả thiết hợp lý và bắt buộc đối với LSP gọi là "*thiết kế dựa trên giao kèo - Design by contract (DBC)*" đưa ra bởi Bertrand Meyer.

Với DBC, tác giả của một class đặt ra các giao kèo cho class đó. Giao kèo thông báo các hành vi mà người khác có thể sử dụng. Giao kèo định nghĩa các điều kiện tiên quyết (preconditions) và các điều kiện sau (postconditions). Điều kiện tiên quyết phải thỏa mãn để phương thức thức hiện. Khi hoàn thành, phương thức đảm bảo rằng các điều kiện sau là đúng.

Chúng ta có thể xem điều kiện sau của *Rectangle.Width* setter như sau:

```C
assert((width == w) && (height == old.height));
```

trong đó *old* là giá trị của *Rectangle* trước khi *Width* được gọi. Quy tắc cho các điều kiện tiên quyết và điều kiện sau của các lớp dẫn xuất được Meyer đưa ra:

> Một hành vi được định nghĩa lại trong một lớp dẫn xuất chỉ có thể thay thế điều kiện tiên quyết bởi một điều kiện bằng hoặc yếu hơn, và thay thế điều kiện sau bằng một điều kiện bằng hoặc mạnh hơn.

Nói cách khác, khi sử dụng một object thông qua interface của class cơ sở, người dùng chỉ biết các điều kiện tiên quyết và điều kiện sau của class cơ sở đó.

* Vì vậy, object của class con không mong họ sẽ tuân thủ điều kiện tiên quyết mạnh hơn class cở sở. Người dùng chỉ phải tuân thủ những gì mà class cơ sở đã chấp nhận.
* Tương tự, các class con phải thỏa mãn tất cả các điều kiện sau. Mọi hành vi và đầu ra không được vi phạm các ràng buộc của class cơ sở. Người dùng class cơ sở sẽ không phải lo lắng về đầu ra của class con.

Ta có thể thấy điều kiện sau của *Square.Width* setter yếu hơn điều kiện sau của *Rectangle.Width* setter khi nó không đảm bảo ràng buộc *height == old.height*. Do đó thuộc tính *Width* của *Square* vi phạm giao kèo của class cơ sở.

> Khái niệm *yếu hơn* hay bị nhầm lẫn. X là yếu hơn Y nếu X không thỏa mãn tất cả các ràng buộc của Y, không quan tâm là X thỏa mãn bao nhiêu ràng buộc khác.

Một số ngôn ngũ như *Eiffel?* hỗ trợ trực tiếp cho điều kiện tiên quyết và điều kiện sau. Nhưng đa số ngôn ngữ khác như *C++* hay *Java* không có tính năng này. Tốt nhất là chúng ta comment các điều kiện cho từng hàm.

## Xác định giao kèo ở Unit Test

Giao kèo có thể được định nghĩa khi viết unit test. Bằng cách kiểm tra các hành vi của class, unit test đảm bảo hành vi của class. Cấc tác giả sử dụng class sẽ dựa trên unit test để đảm bảo các giả thiết hợp lý.

(Ghi tiếp ở phần 2)
