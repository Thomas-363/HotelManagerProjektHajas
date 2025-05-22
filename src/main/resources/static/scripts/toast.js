function showToast(message, type) {
  const container = document.getElementById('toast-container');
  if (!container) return;

  const toast = document.createElement('div');
  toast.textContent = message;


  toast.style.padding = '1rem 1.5rem';
  toast.style.marginBottom = '0.5rem';
  toast.style.borderRadius = '4px';
  toast.style.color = 'white';
  toast.style.fontWeight = 'bold';
  toast.style.boxShadow = '0 2px 6px rgba(0,0,0,0.3)';
  toast.style.opacity = '1';
  toast.style.transition = 'opacity 0.5s ease';


  if (type === 'success') {
    toast.style.backgroundColor = '#4caf50'; // zelená
  } else if (type === 'error') {
    toast.style.backgroundColor = '#f44336'; // červená
  } else {
    toast.style.backgroundColor = '#2196f3'; // modrá info
  }

  container.appendChild(toast);

  setTimeout(() => {
    toast.style.opacity = '0';
    setTimeout(() => {
      if (container.contains(toast)) {
        container.removeChild(toast);
      }
    }, 500);
  }, 3000);
}
