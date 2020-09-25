# Phân biệt hàm (function) và phương thức (method)

Hàm: là một đoạn code tồn tại độc lập được định nghĩa bên ngoài class và được gọi bằng chính tên của hàm.

Phương thức: là các hàm thuộc một class nào đó, được gọi bằng tên phương thức đi kèm với một object của class (hoặc chính class nếu là phương thức static).

Một số ngôn ngữ hướng đối tượng như Java, C# không tồn tại các hàm nằm ngoài class, do đó chỉ tồn tại các phương thức.

Cách đơn giản để nhớ:
* Function -> Free (tự do, không thuộc bất kỳ class nào)
* Method -> Member (thành phần của một class nào đó)

Ví dụ: Ở đoạn code python sau, class Door có một hành động là *open*, ta gọi *open* là phương thức vì nó được định nghĩa bên trong một class. Còn đoạn code định nghĩa hành động *knock_door* được gọi là một hàm, vì nó không nằm trong class nào cả.

```python
class Door:
  def open(self):
    print 'hello stranger'

def knock_door:
  a_door = Door()
  Door.open(a_door)

knock_door()
```
