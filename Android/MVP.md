# MVP

**MVP (Model-View-Presenter)** là một kiến trúc thiết kế giao diện người dùng với mục đích phân tách (decouple) business logic ra khỏi *View* (Activity/Fragment) thông qua một lớp trung gian là *Presenter*. MVP thường giúp code trở nên dễ bảo trì và dễ triển khai automated unit test hơn.

Mô hình chung của MVP như sau:

![image](https://user-images.githubusercontent.com/27339791/112962869-0966dc00-9171-11eb-8602-5afdd3f28acb.png)

## View
View (Activity/Fragment) biểu diễn UI của ứng dụng. View hiển thị dữ liệu lên UI, quản lý các view components và điều hướng user action sang Presenter (ví dụ như sự kiện ấn button). View không tương tác trực tiếp với Model mà phải thông qua Presenter.

## Presenter
Presenter là lớp trung gian giữa View và Model, chịu trách nhiệm xử lý business logic. Presenter gửi yêu cầu lấy dữ liệu tới Model (Repository), nhận dữ liệu phản hồi về, sau đó có thể cập nhật dữ liệu lên View hoặc điều hướng giữa các View.

Thông thường một View sẽ đi kèm một Presenter để xử lý các logic của View đó.

## Model
Model chứa dữ liệu của ứng dụng. Trong Android, Model hoạt động như một Data access layer - lấy data từ local source (SQLite DB, Shared Preference) hoặc remote source (REST API) và trả về cho Presenter.

Không chỉ chứa dữ liệu, Model đồng thời cũng chịu trách nhiệm tạo, cung cấp và fetch data. Nó có thể chạy ở background thread để không block UI thread.

## Một ví dụ về MVP

![image](https://user-images.githubusercontent.com/27339791/113280296-49af9100-930e-11eb-850a-7d5b9df980c2.png)

MainActivity có một cặp *Fragment1 - Presenter1*. Fragment1 triển khai IF *IFragment1Contract::IView*, Presenter1 triển khai IF *IFragment1Contract::IPresenter*.

Fragment1 và Presenter1 tham chiếu lẫn nhau thông qua hai biến mPresenter và mView. Fragment1 gọi các hàm xử lý logic ở Presenter được khai báo trong interface **IPresenter**. Ở chiều ngược lại, *Presenter1* gọi các hàm trên view *Fragment1* được khai báo ở **IView**.

*DbRepository* (Model) có nhiệm vụ query dữ liệu từ Database và trả về Presenter.

## So sánh MVP và MVVM

**MVP:** giải quyết vấn đề phụ thuộc **View** bằng cách thêm một lớp trung gian **Presenter** như một kênh giao tiếp giữa View và Model.

**MVVM** thiết kế mang tính event-driven khi sử dụng data binding giúp tách biệt giữa business logic ra khỏi **View**. 

Một số điểm khác biệt như sau:

| MVP | MVVM |
| --- |:----:|
| Có quan hệ **1:1** giữa View và Presenter | Nhiều View có thể map tới cùng một ViewModel |
| Presenter có tham chiếu tới View | ViewModel không tham chiếu tới View |
| Model phản hồi dữ liệu về Presenter, Presenter sẽ phản hồi lại View | Model trả dữ liệu về View thông qua cơ chế data binding |
| Presenter xử lý luồng ứng dụng, View đóng vai trò là ứng dụng thật sự | ViewModel là ứng dụng thật sự, View là interface để user tương tác với ứng dụng? |


## Tham khảo

[1] [https://medium.com/android-news/architectural-guidelines-to-follow-for-mvp-pattern-in-android-2374848a0157](https://medium.com/android-news/architectural-guidelines-to-follow-for-mvp-pattern-in-android-2374848a0157)

[2] [https://www.vogella.com/tutorials/AndroidArchitecture/article.html](https://www.vogella.com/tutorials/AndroidArchitecture/article.html)

[3] [https://www.geeksforgeeks.org/difference-between-mvp-and-mvvm-architecture-pattern-in-android/](https://www.geeksforgeeks.org/difference-between-mvp-and-mvvm-architecture-pattern-in-android/)
