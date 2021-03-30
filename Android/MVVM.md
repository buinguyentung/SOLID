# MVVM

**MVVM (Model - View - View Model)** là một pattern phổ biến trong thiết kế các ứng dụng Android.

Android app bao gồm nhiều Activity hoặc Fragment. Những thành phần này thường chứa cả UI logic và business logic. Nó làm cho việc viết unit test, bảo trì, hay mở rộng ứng dụng trở nên khó khăn hơn. Từ đó các pattern như MVVM hay MVP ra đời nhằm mục đích giảm thiểu các vấn đề trên.

MVVM chia tách các view (Activity hoặc Fragment) riêng biệt ra khỏi business logic. Một mô hình MVVM tổng quát như sau:

![image](https://user-images.githubusercontent.com/27339791/112925044-69db2680-913b-11eb-8b37-52cd6d084a43.png)

## View
Một thành phần view biểu diễn một phần UI của ứng dụng. View nhận biết (observe) sự thay đổi của dữ liệu bằng cách *bind* các biến hoặc hành động được cung cấp bởi ViewModel thông qua data binding framework.

View chịu trách nhiệm chính xử lý UI logic:

* Permissions
* Event listeners
* Hiển thị dialogs, toasts, etc.
* Khởi chạy activities
* Làm việc với Android View và Widget
* Tất cả các chức năng liên quan tới Android Context

## View Model
ViewModel hỗ trợ xử lý data yêu cầu bởi View. Nó giống như là một lớp trừu tượng của View, cung cấp public các thuộc tính và hàm. Nó sử dụng observable data để thông báo sự thay đổi tới View. Nó cho phép chuyển các event tới Model. Và nó cũng đồng thời hoạt động như một data converter - chuyển các giá trị từ raw model data sang các thuộc tính thân thiện dễ xử lý hơn.

ViewModel chịu các trách nhiệm sau:

* Cung cấp dữ liệu
* Cung cấp state (progress, offline, empty, error, etc.)
* Xử lý khả năng hiển thị
* Kiểm tra validate input
* Xử lý các hàm gọi tới model
* Xử lý các phương thức trong view

ViewModel nên biết về các Application Context. Application Context có thể:

* Start một service
* Bind một service
* Gửi một broadcast
* Đăng ký một broadcast receiver
* Load resource values

ViewModel không thể xử lý các nhiệm vụ của View:

* Show một dialog
* Start một activity
* Inflate một layout

## Model
Thường chứa các cấu trúc dữ liệu và một thành phần quản lý *Data Provider (Repository)*. Repository chịu trách nhiệm lấy và update data từ local source hoặc remote source và phản hồi lại cho ViewModel. Data có thể được lấy từ nhiều nguồn ví dụ như:

* SQLite DB
* Shared Preferences
* Realm DB
* Handles broadcast
* REST API
* Firebase

## Sự khác biệt với MVP
MVVM sử dụng data binding, do đó nó là một kiến trúc kiểu event-driven. Thông thường MVP duy trì quan hệ **một-một** giữa View-Presenter, trong khi MVVM có thể map nhiều View tới một ViewModel. Trong MVVM, View có tham chiếu tới ViewModel nhưng ViewModel không có tham chiếu tới View, ngược lại ở MVP, View và Presenter luôn có tham chiếu lẫn nhau.

Ví dụ một mô hình dọc của MVVM:
![image](https://user-images.githubusercontent.com/27339791/112925876-c8ed6b00-913c-11eb-8253-2ff823d6fc9d.png)


## Ví dụ đơn giản về MVVM

[MVVM Java simple example about Holidays](https://github.com/kashifo/EasyMVVM/blob/master/app/src/main/java/com/github/easymvvm/java/HolidayAdapter.java)

Các thành phần chính trong ví dụ bao gồm:

* HolidayActivity, HolidayAdapter: Xử lý UI logic, kiểm tra Internet permission/connection, đăng ký lắng nghe sự kiện cập nhật holidays từ ViewModel, hiển thị danh sách các ngày nghỉ lễ lên UI khi có phản hồi.
* HolidayViewModel: Cung cấp chức năng lấy danh sách các ngày nghỉ (*getHolidays*) từ Repository và phản hồi về View.
* HolidayModel, HolidayRepo: Request danh sách ngày nghỉ thông qua WebAPI và trả về ViewModel.


## Tham khảo
[1] [https://www.vogella.com/tutorials/AndroidArchitecture/article.html](https://www.vogella.com/tutorials/AndroidArchitecture/article.html)

[2] [https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture)
