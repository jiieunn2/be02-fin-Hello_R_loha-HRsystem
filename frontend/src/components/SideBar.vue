<template>

    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-gauge-high"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="gauge-high"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256zM288 96a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM256 416c35.3 0 64-28.7 64-64c0-17.4-6.9-33.1-18.1-44.6L366 161.7c5.3-12.1-.2-26.3-12.3-31.6s-26.3 .2-31.6 12.3L257.9 288c-.6 0-1.3 0-1.9 0c-35.3 0-64 28.7-64 64s28.7 64 64 64zM176 144a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM96 288a32 32 0 1 0 0-64 32 32 0 1 0 0 64zm352-32a32 32 0 1 0 -64 0 32 32 0 1 0 64 0z">
                                        </path>
                                    </svg><!-- <i class="fas fa-tachometer-alt"></i> Font Awesome fontawesome.com -->
                                </div>
                                메인 화면
                            </a>
                            <div class="sb-sidenav-menu-heading">Interface</div>
                            <a class="nav-link collapsed" href="/" data-bs-toggle="collapse"
                                data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-table-columns"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="table-columns"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M0 96C0 60.7 28.7 32 64 32H448c35.3 0 64 28.7 64 64V416c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V96zm64 64V416H224V160H64zm384 0H288V416H448V160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-columns"></i> Font Awesome fontawesome.com --></div>
                                회원가입
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>

                            <a class="nav-link collapsed" href="/login" data-bs-toggle="collapse"
                                data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-book-open"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="book-open"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M249.6 471.5c10.8 3.8 22.4-4.1 22.4-15.5V78.6c0-4.2-1.6-8.4-5-11C247.4 52 202.4 32 144 32C93.5 32 46.3 45.3 18.1 56.1C6.8 60.5 0 71.7 0 83.8V454.1c0 11.9 12.8 20.2 24.1 16.5C55.6 460.1 105.5 448 144 448c33.9 0 79 14 105.6 23.5zm76.8 0C353 462 398.1 448 432 448c38.5 0 88.4 12.1 119.9 22.6c11.3 3.8 24.1-4.6 24.1-16.5V83.8c0-12.1-6.8-23.3-18.1-27.6C529.7 45.3 482.5 32 432 32c-58.4 0-103.4 20-123 35.6c-3.3 2.6-5 6.8-5 11V456c0 11.4 11.7 19.3 22.4 15.5z">
                                        </path>
                                    </svg><!-- <i class="fas fa-book-open"></i> Font Awesome fontawesome.com --></div>
                                로그인
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-book-open"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="book-open"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M249.6 471.5c10.8 3.8 22.4-4.1 22.4-15.5V78.6c0-4.2-1.6-8.4-5-11C247.4 52 202.4 32 144 32C93.5 32 46.3 45.3 18.1 56.1C6.8 60.5 0 71.7 0 83.8V454.1c0 11.9 12.8 20.2 24.1 16.5C55.6 460.1 105.5 448 144 448c33.9 0 79 14 105.6 23.5zm76.8 0C353 462 398.1 448 432 448c38.5 0 88.4 12.1 119.9 22.6c11.3 3.8 24.1-4.6 24.1-16.5V83.8c0-12.1-6.8-23.3-18.1-27.6C529.7 45.3 482.5 32 432 32c-58.4 0-103.4 20-123 35.6c-3.3 2.6-5 6.8-5 11V456c0 11.4 11.7 19.3 22.4 15.5z">
                                        </path>
                                    </svg><!-- <i class="fas fa-book-open"></i> Font Awesome fontawesome.com --></div>
                                출퇴근
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-book-open"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="book-open"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M249.6 471.5c10.8 3.8 22.4-4.1 22.4-15.5V78.6c0-4.2-1.6-8.4-5-11C247.4 52 202.4 32 144 32C93.5 32 46.3 45.3 18.1 56.1C6.8 60.5 0 71.7 0 83.8V454.1c0 11.9 12.8 20.2 24.1 16.5C55.6 460.1 105.5 448 144 448c33.9 0 79 14 105.6 23.5zm76.8 0C353 462 398.1 448 432 448c38.5 0 88.4 12.1 119.9 22.6c11.3 3.8 24.1-4.6 24.1-16.5V83.8c0-12.1-6.8-23.3-18.1-27.6C529.7 45.3 482.5 32 432 32c-58.4 0-103.4 20-123 35.6c-3.3 2.6-5 6.8-5 11V456c0 11.4 11.7 19.3 22.4 15.5z">
                                        </path>
                                    </svg><!-- <i class="fas fa-book-open"></i> Font Awesome fontawesome.com --></div>
                                휴가
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-book-open"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="book-open"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M249.6 471.5c10.8 3.8 22.4-4.1 22.4-15.5V78.6c0-4.2-1.6-8.4-5-11C247.4 52 202.4 32 144 32C93.5 32 46.3 45.3 18.1 56.1C6.8 60.5 0 71.7 0 83.8V454.1c0 11.9 12.8 20.2 24.1 16.5C55.6 460.1 105.5 448 144 448c33.9 0 79 14 105.6 23.5zm76.8 0C353 462 398.1 448 432 448c38.5 0 88.4 12.1 119.9 22.6c11.3 3.8 24.1-4.6 24.1-16.5V83.8c0-12.1-6.8-23.3-18.1-27.6C529.7 45.3 482.5 32 432 32c-58.4 0-103.4 20-123 35.6c-3.3 2.6-5 6.8-5 11V456c0 11.4 11.7 19.3 22.4 15.5z">
                                        </path>
                                    </svg><!-- <i class="fas fa-book-open"></i> Font Awesome fontawesome.com --></div>
                                결제
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><svg class="svg-inline--fa fa-book-open"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="book-open"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M249.6 471.5c10.8 3.8 22.4-4.1 22.4-15.5V78.6c0-4.2-1.6-8.4-5-11C247.4 52 202.4 32 144 32C93.5 32 46.3 45.3 18.1 56.1C6.8 60.5 0 71.7 0 83.8V454.1c0 11.9 12.8 20.2 24.1 16.5C55.6 460.1 105.5 448 144 448c33.9 0 79 14 105.6 23.5zm76.8 0C353 462 398.1 448 432 448c38.5 0 88.4 12.1 119.9 22.6c11.3 3.8 24.1-4.6 24.1-16.5V83.8c0-12.1-6.8-23.3-18.1-27.6C529.7 45.3 482.5 32 432 32c-58.4 0-103.4 20-123 35.6c-3.3 2.6-5 6.8-5 11V456c0 11.4 11.7 19.3 22.4 15.5z">
                                        </path>
                                    </svg><!-- <i class="fas fa-book-open"></i> Font Awesome fontawesome.com --></div>
                                초과근무
                                <div class="sb-sidenav-collapse-arrow"><svg class="svg-inline--fa fa-angle-down"
                                        aria-hidden="true" focusable="false" data-prefix="fas" data-icon="angle-down"
                                        role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"
                                        data-fa-i2svg="">
                                        <path fill="currentColor"
                                            d="M169.4 342.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 274.7 54.6 137.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z">
                                        </path>
                                    </svg><!-- <i class="fas fa-angle-down"></i> Font Awesome fontawesome.com --></div>
                            </a>


                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Start Bootstrap
                    </div>
                </nav>
            </div>

        </div>







        <!-- Code injected by live-server -->



    </body>

</template>

<script>


export default {
    name: 'SideBar',
    components: {

    }
}
</script>



<style>
</style>