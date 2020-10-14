# The Liskov Substitution Principle (LSP) Part 2

## Project thực tế

Những năm đầu thập niên 1990, tác giả mua một thư viện third-party có chứa một số container class liên quan tới *Bag* và *Set* của Smalltalk. Có hai biến thể của *Set* và hai biến thể tương tự của *Bag*. Biến thể đầu tiên là kiểu ràng buộc (*bounded*) dựa trên một mảng (array). Biến thể thứ hai là kiểu không ràng buộc (*unbounded*) dựa trên một danh sách liên kết (linked list).

![image](https://user-images.githubusercontent.com/27339791/95930974-2477a680-0df2-11eb-8ce5-d7cd7695d1b8.png)

![image](https://user-images.githubusercontent.com/27339791/95931008-440ecf00-0df2-11eb-8749-0f6693feeb55.png)
