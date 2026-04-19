# De13_23110236

Dự án web bán hàng xây dựng bằng Spring Boot, hiển thị bằng Thymeleaf, lấy dữ liệu qua Spring Data JPA và lưu trữ trên Microsoft SQL Server. Ứng dụng hỗ trợ 3 vai trò chính: Admin, Seller và User.

## Tính năng chính

- Trang chủ hiển thị danh sách sản phẩm.
- Xem chi tiết sản phẩm.
- Lọc sản phẩm theo danh mục, xem sản phẩm theo seller.
- Đăng ký, đăng nhập, đăng xuất theo phiên lưu session.
- Phân luồng giao diện theo vai trò:
  - Admin: quản lý danh mục, sản phẩm, seller, người dùng.
  - Seller: quản lý sản phẩm của riêng mình, thêm/sửa/xóa, xem thống kê.
  - User: duyệt sản phẩm và xem chi tiết.
- Upload ảnh qua API `/api/upload`.

## Công nghệ

- Java 21
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- Thymeleaf
- Thymeleaf Layout Dialect
- Validation
- Microsoft SQL Server
- Lombok

## Yêu cầu môi trường

- JDK 21
- Maven 3.9+ hoặc Maven Wrapper
- SQL Server đang chạy tại `localhost:1433`
- Database `De13_23110236`

## Cài đặt database

File `complete_database_setup.sql` đã bao gồm script tạo database, bảng và dữ liệu mẫu.

1. Mở SQL Server Management Studio.
2. Chạy file `complete_database_setup.sql`.
3. Đảm bảo database `De13_23110236` được tạo thành công.

Nếu bạn muốn dùng database khác, hãy sửa lại các giá trị trong `src/main/resources/application.properties`.

## Cài đặt và chạy ứng dụng

### 1. Clone hoặc mở project

```bash
git clone <repository-url>
cd De13_23110236
```

### 2. Cấu hình database

Mở `src/main/resources/application.properties` và kiểm tra các giá trị sau:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=De13_23110236;encrypt=false;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=1
```

### 3. Chạy ứng dụng

Trên Windows:

```bash
mvnw.cmd spring-boot:run
```

Hoặc nếu đã cài Maven:

```bash
mvn spring-boot:run
```

### 4. Truy cập trình duyệt

```text
http://localhost:8080
```

## Tài khoản mẫu

Nếu chạy theo file `complete_database_setup.sql`, có thể đăng nhập bằng các tài khoản sau:

- Admin: `admin@example.com` / `123456`
- Seller: `seller1@example.com` / `123456`
- User: `user1@example.com` / `123456`

## Luồng chính

- Khách truy cập trang chủ, danh sách sản phẩm và chi tiết sản phẩm.
- Người dùng đăng ký / đăng nhập.
- Hệ thống tự chuyển hướng theo vai trò sang màn hình phù hợp.
- Seller quản lý sản phẩm của riêng mình.
- Admin quản lý hệ thống tại khu vực `/admin`.

## API upload ảnh

- `POST /api/upload`: upload file hình ảnh.
- `DELETE /api/delete/{filename}`: xóa file đã upload.

File ảnh được lưu trong `src/main/resources/static/images/uploads`.

## Cấu trúc dự án

```text
src/
  main/
    java/vn/iot/
      De1323110236Application.java
      config/
      controller/
      model/
      repository/
      service/
    resources/
      application.properties
      static/
        css/
        images/uploads/
      templates/
        admin/
        auth/
        components/
        home/
        layout/
        products/
        seller/
  test/
```

- `De1323110236Application.java`: điểm khởi động của Spring Boot.
- `config/`: cấu hình hệ thống, khởi tạo dữ liệu và cấu hình Hibernate nếu có.
- `controller/`: xử lý request/response cho trang chủ, đăng nhập, sản phẩm, seller, admin và upload ảnh.
- `model/`: các entity JPA như `Users`, `UserRoles`, `Seller`, `Category`, `Product`, `Cart`, `CartItem`.
- `repository/`: tầng truy cập dữ liệu với Spring Data JPA.
- `service/`: nghiệp vụ xử lý dữ liệu, gọi repository và điều phối logic giữa các lớp.
- `resources/application.properties`: cấu hình database, server, Thymeleaf và upload file.
- `resources/templates/`: giao diện Thymeleaf, chia theo từng nhóm chức năng.
- `resources/static/`: tài nguyên tĩnh như CSS và ảnh upload.
- `test/`: mã kiểm thử của dự án.

## Ghi chú

- Dự án sẽ tự động tạo schema khi `spring.jpa.hibernate.ddl-auto=update` được giữ nguyên.
- Nếu đã chèn dữ liệu bằng script SQL, nên đối chiếu lại entity và field mapping tương ứng.

## Tac gia
- Ho ten: Nguyen Nuoc Khang
- Email: khangnguyen2x0@gmail.com