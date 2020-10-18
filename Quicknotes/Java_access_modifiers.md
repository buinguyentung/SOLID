# Modifier

Có hai loại modifier trong Java là:

* **Access modifier:** private, default, protected, public
* **Non-access modifier:** static, abstract, synchronized, native, volatile, transient...

Trong bài này chúng ta tập trung vào **access modifier**.

Access modifier được dùng khi khai báo class, biến, phương thức. Trong đó các class chỉ có thể khai báo *public* hoặc *default* (ngoại trừ các nested class - class được khai báo trong một class khác).

Access modifier thể hiện khả năng truy cập ở class khác, package khác.

* Private: chỉ truy cập trong nội bộ class
* Default: chỉ truy cập trong nội bộ package. Mặc định khi không khai báo access modifier nào.
* Protected: chỉ truy cập trong nội bộ package, hoặc bên ngoài package thông qua class con.
* Public: truy cập tự do từ bên ngoài class, package.

| Access modifier | Bên trong class | Bên trong package | Bên ngoài package / qua class con | Bên ngoài package |
| --------- |:-----:|:-----:|:-----:|:-----:|
| Private   | Y | - | - | - |
| Default   | Y | Y | - | - |
| Protected | Y | Y | Y | - |
| Public    | Y | Y | Y | Y |

## Ví dụ

Trong package *a*, Class *Message* có 4 members message a, b, c, d lần lượt thuộc kiểu private, default, protected, và public.

Class *Client* thuộc package *b* thực hiện truy xuất các message trong class *Message*.

* message_a là kiểu *private*: chỉ có thể truy cập thông qua phương thức getter được public trong class *Message*.
* message_b là kiểu *default*: có thể truy cập thông qua class *MessageBHandler* là class con của class *Message* thuộc cùng package *a*.
* message_c là kiểu *protected*: có thể truy cập thông qua class *MessageCHandler* là class con của class *Message*, thuộc package *b* khác.
* message_d là kiểu *public*: có thể truy cập trực tiếp.

![image](https://user-images.githubusercontent.com/27339791/96357318-725b1a00-1124-11eb-8ba2-0873c14f8fe2.png)

Sample code: [Sample Java access modifier](https://github.com/buinguyentung/SOLID/blob/master/Projects/AccessModifier/src/bpackage/Client.java)
