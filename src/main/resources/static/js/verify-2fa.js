document.addEventListener('DOMContentLoaded', function() {
    const codeInput = document.getElementById('code');
    const form = document.querySelector('.login-form');
    
    // Auto-avance y validación en tiempo real
    codeInput.addEventListener('input', function(e) {
        // Solo números y máximo 6 dígitos
        this.value = this.value.replace(/[^0-9]/g, '').substring(0, 6);
        
        // Auto-submit cuando complete 6 dígitos
        if (this.value.length === 6) {
            form.submit();
        }
    });
    
    // Prevenir envío si no son 6 dígitos
    form.addEventListener('submit', function(e) {
        if (codeInput.value.length !== 6) {
            e.preventDefault();
            alert('Por favor ingresa los 6 dígitos completos');
            codeInput.focus();
        }
    });
    
    // Enfocar el input al cargar la página
    codeInput.focus();
});