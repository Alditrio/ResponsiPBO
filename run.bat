@echo off
echo ==============================================
echo Mengompilasi source code Java...
javac *.java
if %errorlevel% neq 0 (
    echo [ERROR] Kompilasi gagal!
    pause
    exit /b %errorlevel%
)
echo [OK] Kompilasi berhasil!
echo.
echo ==============================================
echo Menjalankan Main...
echo ==============================================
java Main
echo.
pause
