package com.java6.asm.clothing_store.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 🔥 Lỗi liên quan đến User
    USER_EXISTED(1001, "Người dùng đã tồn tại!", HttpStatus.CONFLICT),
    USER_NOT_EXISTED(1005, "Người dùng không tồn tại!", HttpStatus.NOT_FOUND),
    EMAIL_INVALID(1003, "Email người dùng không hợp lệ hoặc đang trống!", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Mật khẩu không hợp lệ hoặc đang trống!", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED_EXCEPTION(1006, "Lỗi xác thực!", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(1007, "Email hoặc mật khẩu không đúng!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "Bạn không có quyền thực hiện thao tác này!", HttpStatus.FORBIDDEN),


    // 🔥 Lỗi liên quan đến Token
    REFRESH_TOKEN_INVALID(2001, "Refresh Token không hợp lệ hoặc đã hết hạn!", HttpStatus.UNAUTHORIZED),
    ACCESS_TOKEN_INVALID(2002, "Access Token không hợp lệ hoặc đã hết hạn!", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(2003, "Token đã hết hạn, vui lòng đăng nhập lại!", HttpStatus.UNAUTHORIZED),
    TOKEN_MISSING(2004, "Không tìm thấy Token!", HttpStatus.UNAUTHORIZED),
    TOO_MANY_DEVICES(2005, "Giới hạn đăng nhập là 3 thiết bị", HttpStatus.OK),

    // 🔥 Lỗi liên quan đến Sản phẩm
    PRODUCT_NOT_EXISTED(3001, "Sản phẩm không tồn tại!", HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTED(3005, "Sản phẩm đã tồn tại!", HttpStatus.CONFLICT),
    INVALID_QUANTITY(3002, "Số lượng không phù hợp hoặc hết hàng", HttpStatus.BAD_REQUEST),

    // 🔥 Lỗi liên quan đến Danh mục
    CATEGORY_EXISTED(4001, "Danh mục đã tồn tại!", HttpStatus.CONFLICT),
    CATEGORY_NOT_EXISTED(4005, "Danh mục không tồn tại!", HttpStatus.NOT_FOUND),

    // 🔥 Lỗi liên quan đến Giỏ hàng
    CART_ITEM_ALREADY_EXISTS(5001, "Sản phẩm đã có trong giỏ hàng!", HttpStatus.CONFLICT),
    CART_ITEM_NOT_FOUND(5002, "Không tìm thấy sản phẩm trong giỏ hàng!", HttpStatus.NOT_FOUND),
    INVALID_CART_ITEM(5003, "Sản phẩm trong giỏ hàng không hợp lệ!", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(5004, "Giỏ hàng không tồn tại", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(5005, "Sản phẩm không tồn tại", HttpStatus.NOT_FOUND),

    // 🔥 Lỗi liên quan đến Đơn hàng
    ORDER_NOT_FOUND(6001, "Không tìm thấy đơn hàng!", HttpStatus.NOT_FOUND),
    ORDER_ALREADY_EXISTED(6005, "Đơn hàng đã tồn tại!", HttpStatus.CONFLICT),
    ORDER_NOT_PENDING(6010, "Chỉ có thể xác nhận đơn hàng đang chờ xử lý!", HttpStatus.CONFLICT),
    ORDER_NOT_PROCESSING(6020, "Chỉ có thể giao đơn hàng đang xử lý!", HttpStatus.CONFLICT),
    ORDER_NOT_SHIPPING(6030, "Chỉ có thể hoàn tất đơn hàng đang vận chuyển!", HttpStatus.CONFLICT),
    ORDER_NOT_CANCEL(6040, "Không thể hủy đơn hàng đã hoàn tất!", HttpStatus.CONFLICT),

    // 🔥 Lỗi liên quan đến Ảnh đại diện
    AVATAR_NOT_PERMISSION(7001, "Bạn không thể thay đổi ảnh đại diện của người khác!", HttpStatus.FORBIDDEN),

    // 🔥 Lỗi hệ thống chung
    INVALID_KEY(8001, "Khóa không hợp lệ!", HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER(8002, "Tham số không hợp lệ!", HttpStatus.BAD_REQUEST),
    RESPONSE_NOT_FOUND(8003, "Không tìm thấy phản hồi!", HttpStatus.NOT_FOUND),
    INVALID_REQUEST_BODY(8004, "Dữ liệu gửi lên không hợp lệ!", HttpStatus.BAD_REQUEST),
    CREATE_PAYMENT_FAILED(8005, "Thanh toán thất bại!", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi không xác định!", HttpStatus.INTERNAL_SERVER_ERROR);



    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
