function nuevoPedido() {
    alert('Función: Nuevo Pedido - Próximamente');
}

function gestionarMenu() {
    alert('Función: Gestionar Menú - Próximamente');
}

function verInventario() {
    alert('Función: Ver Inventario - Próximamente');
}

function reportes() {
    alert('Función: Reportes - Próximamente');
}

// Actualizar métricas en tiempo real
function updateMetrics() {
    // Simular actualización de datos
    const metrics = document.querySelectorAll('.metric-value');
    metrics.forEach(metric => {
        metric.style.transform = 'scale(1.1)';
        setTimeout(() => {
            metric.style.transform = 'scale(1)';
        }, 200);
    });
}

// Simular actualizaciones cada 30 segundos
setInterval(updateMetrics, 30000);

// Efectos de interacción
document.addEventListener('DOMContentLoaded', function() {
    const navItems = document.querySelectorAll('.nav-item');
    
    navItems.forEach(item => {
        item.addEventListener('click', function(e) {
            navItems.forEach(nav => nav.classList.remove('active'));
            this.classList.add('active');
        });
    });
});