(function(){
  const btn = document.querySelector('.nav-toggle');
  const nav = document.getElementById('primary-nav');
  if(btn && nav){
    btn.addEventListener('click', () => {
      const open = nav.style.display === 'flex';
      nav.style.display = open ? 'none' : 'flex';
      btn.setAttribute('aria-expanded', String(!open));
    });
  }
  // basic form handler (no network calls)
  const form = document.querySelector('form');
  if(form){
    form.addEventListener('submit', (e)=>{
      e.preventDefault();
      alert('문의가 임시로 저장되었습니다. 실제 전송 로직은 백엔드 연결 후 구성하세요.');
      form.reset();
    });
  }
})();