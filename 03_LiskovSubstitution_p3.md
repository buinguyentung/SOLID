# Ví dụ LSP đơn giản

Chúng ta đã đọc xong chương trình bày về nguyên lý thiết kế LSP của tác giả Robert Martin. Nguyên lý này yêu cầu khi thiết kế chúng ta phải đảm bảo rằng *các lớp con có thể chạy đúng những chức năng sử dụng lớp cha đã cung cấp trước đó*.

Đã đến lúc để thực hành bằng một ví dụ đơn giản về LSP. Thử tưởng tượng chúng ta tập hợp một nhóm các kỹ sư và bắt đầu một start-up. Ta thiết kế mô hình quản lý công ty: lớp cơ sở *Engineer* và hai lớp con *EmbeddedEngineer* và *CloudEngineer*. Lớp cơ sở *Engineer* có các phương thức get/set *name* và *writeCode*. Nhóm của chúng ta ai cũng code được cơ mà.

![image](https://user-images.githubusercontent.com/27339791/97316869-d0ae9800-189c-11eb-8e11-c202db448e3b.png)

Mọi chuyện bắt đầu suôn sẻ, start-up ăn nên làm ra, quy mô dần mở rộng. Cho đến một ngày chúng ta quyết định sẽ tự xây dựng một đội tester của chính công ty thay vì thuê outsource chi phí ngày càng cao. Ta tạo một class con *TestEngineer* kế thừa từ class cha *Engineer* để quản lý.

![image](https://user-images.githubusercontent.com/27339791/97317168-1bc8ab00-189d-11eb-9e6d-e381f59ba8a0.png)

Có một vấn đề xuất hiện là *tester không hề biết code*. Ta tạo RuntimeException trong phương thức *writeCode*, rồi tự đặt ra quy ước rằng không ai được phép yêu cầu tester lập trình.

![image](https://user-images.githubusercontent.com/27339791/96944689-b3c53e00-1505-11eb-83bb-bf46ab09ff99.png)

Tham khảo: [Engineer Code không thỏa mãn LSP](https://github.com/buinguyentung/SOLID/blob/master/Projects/04_EngineerForLSP/src/bnt/lsp/EngineerExample1.java)

Chương trình không có lỗi biên dịch và chạy ổn. Cho tới khi một lập trình viên mới vào làm việc trong công ty và bỗng nhiên chương trình bị crash khi cậu ấy quên đi quy ước khi thêm một tester vào danh sách code engineer.

![image](https://user-images.githubusercontent.com/27339791/96944777-fa1a9d00-1505-11eb-80ed-50928523bfd6.png)

## Sửa chữa

Có hai cách thiết kế để sửa chữa vấn đề này. Đảm bảo chương trình báo lỗi ngay khi developer biên dịch.

Cách thứ nhất là tách phương thức *writeCode* ra một interface riêng gọi là *ICoder*, những class nào sử dụng được phương thức đó thì sẽ implement interface *ICoder*.

![image](https://user-images.githubusercontent.com/27339791/97319203-3dc32d00-189f-11eb-9eb3-24c7d9fa0db6.png)

```java
interface ICoder {
    void writeCode();
}

class EmbeddedEngineer extends Engineer implements ICoder {
    public EmbeddedEngineer(String name) {
        super(name);
    }

    @Override
    public void writeCode() {
        System.out.println(this.getName() + " do code for embedded devices");
    }
}

class TestEngineer extends Engineer {
    public TestEngineer(String name) {
        super(name);
    }
}
```

Chương trình sẽ báo lỗi biên dịch ngay khi chúng ta có ý định thêm một instance của class *TestEngineer* vào danh sách coder.

```java
EmbeddedEngineer engineer1 = new EmbeddedEngineer("TungBN");
CloudEngineer engineer2 = new CloudEngineer("VietVH");
TestEngineer engineer3 = new TestEngineer("HaiHV");

List<Engineer> engineers = new ArrayList<>();
engineers.add(engineer1);
engineers.add(engineer2);
engineers.add(engineer3);

List<ICoder> coders = new ArrayList<>();
coders.add(engineer1);
coders.add(engineer2);
// coders.add(engineer3); // Error
```

Tham khảo: [Engineer Code thỏa mãn LSP](https://github.com/buinguyentung/SOLID/blob/master/Projects/05_EngineerForLSP/src/bnt/lsp/EngineerExample2.java)

Cách này có nhược điểm là chúng ta sẽ phải thay thế nhiều hàm đang có sẵn mà hoạt động với class *Engineer* có gọi tới phương thức *writeCode* sang sử dụng interface *ICoder*.

Cách thứ hai là tạo một base class *Employee* có quản lý việc get/set name. *Engineer* và *TestEngineer* là họ hàng cùng kế thừa class *Employee*.

![image](https://user-images.githubusercontent.com/27339791/97322503-7284b380-18a2-11eb-82ac-a76fd4306469.png)

Các phương thức nào cần xử lý chung cho toàn bộ nhân viên sẽ sử dụng class *Employee*. Còn các phương thức đang xử lý với class *Engineer* (writeCode) vẫn được giữ nguyên.
