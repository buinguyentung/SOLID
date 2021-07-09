# Class Diagram – Generalization & Realization

Class diagram là *UML structure diagram*, nó thể hiện low level design của application.

Class diagram biểu diễn các object trong hệ thống và mối quan hệ giữa chúng. Class diagram giúp người đọc hiểu được các trạng thái và hành vi của các object.

Nhìn chung có 3 loại quan hệ chính giữa các object là:

+ *Generalization và Realization*
+ Association
+ Dependency/Uses

Ở bài này ta tìm hiểu về **Generalization và Realization.**
Và sự tương quan giữa chúng với các từ khóa **extends và implements.**

## Generalization

Trong mối quan hệ **generalization / inheritance / is-a**, các kiểu con được coi là các triển khai chuyên biệt của kiểu cha. Kiểu cha định nghĩa các trạng thái và hành vi đặc trưng, chúng có thể được sử dụng lại ở các kiểu con của nó. Nói cách khác, kiểu cha là sự khái quát (generalization) của các kiểu con.

UML quy định generalization được trình bày bằng một mũi tên liền, xuất phát từ kiểu con, đầu kết thúc là hình *tam giác rỗng* trỏ vào kiểu cha.

![image](https://user-images.githubusercontent.com/27339791/125023278-5ccfac80-e033-11eb-8e02-b05b722ef6fd.png)

```java
public class Employee {

}

public class Engineer extends Employee {

}
```

## Realization

Đối với mối quan hệ Realization, kiểu con triển khai hay là hiện thực hóa một kiểu cha trừu tượng (abstract type).

![image](https://user-images.githubusercontent.com/27339791/125023662-2c3c4280-e034-11eb-93ce-3a16b4ef7785.png)

UML quy định realization là một mũi tên gồm các *nét đứt* xuất phát từ kiểu con, và kết thúc bằng một hình *tam giác rỗng* trỏ vào kiểu cha.

```java
public interface Drivable {

}

public class Vehicle implements Drivable {

}
```

Note: Cả generalization và realization đều là quan hệ ở class level, hay là ở lúc biên dịch (compile time).

## extends và implements

Xem xét hình minh họa sau:

![image](https://user-images.githubusercontent.com/27339791/124905452-e41d1180-df9a-11eb-87d8-e808392750d8.png)

Có thể thấy:
+ **Generalization**: được dùng để biểu diễn mối quan hệ giữa class-class hoặc interface-interface (**extends**).
+ **Realization**: được dùng để biểu diễn mối quan hệ giữa class-interface (**implements**).

Reference:
https://ourownjava.com/uml/uml-class-diagram-generalization-realization/
