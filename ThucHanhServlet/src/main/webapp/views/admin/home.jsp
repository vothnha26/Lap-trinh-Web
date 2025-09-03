<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Quản trị</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        .feature-card {
            transition: transform .2s;
        }
        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="p-5 mb-4 bg-light rounded-3">
            <div class="container-fluid py-5">
                <h1 class="display-5 fw-bold">Admin Dashboard</h1>
                <p class="col-md-8 fs-4">Chào mừng, <strong>${sessionScope.account.fullName}</strong>! Chọn một chức năng để bắt đầu quản lý.</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm feature-card">
                    <div class="card-body text-center">
                        <i class="fas fa-tasks fa-3x text-primary mb-3"></i>
                        <h5 class="card-title">Quản lý Danh mục</h5>
                        <p class="card-text">Thêm, sửa, xóa các danh mục sản phẩm.</p>
                        <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-primary">
                            Đi đến <i class="fas fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm feature-card">
                    <div class="card-body text-center">
                        <i class="fas fa-users-cog fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Quản lý Người dùng</h5>
                        <p class="card-text">Quản lý tài khoản người dùng trong hệ thống.</p>
                        <a href="#" class="btn btn-success">
                             Đi đến <i class="fas fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm feature-card">
                    <div class="card-body text-center">
                        <i class="fas fa-box-open fa-3x text-info mb-3"></i>
                        <h5 class="card-title">Quản lý Sản phẩm</h5>
                        <p class="card-text">Quản lý thông tin các sản phẩm.</p>
                        <a href="#" class="btn btn-info">
                            Đi đến <i class="fas fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
         <div class="mt-4">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                <i class="fas fa-home me-2"></i>Về trang chủ
            </a>
        </div>
    </div>
</body>
</html>