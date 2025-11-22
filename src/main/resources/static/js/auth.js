document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.login-form');
    
    loginForm.addEventListener('submit', function(e) {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        
        if (!username || !password) {
            e.preventDefault();
            alert('Por favor completa todos los campos');
            return;
        }
        
        // Efecto de carga en el botón
        const submitBtn = this.querySelector('.login-btn');
        submitBtn.textContent = 'Iniciando sesión...';
        submitBtn.disabled = true;
    });
    
    // Efectos de interacción en los inputs
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.parentElement.style.transform = 'scale(1.02)';
        });
        
        input.addEventListener('blur', function() {
            this.parentElement.style.transform = 'scale(1)';
        });
    });
});