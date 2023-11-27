document.addEventListener("DOMContentLoaded", function () {
    const carruselInner = document.querySelector('.carrusel-inner');
    const slides = document.querySelectorAll('.carrusel-slide');
    let currentIndex = 0;

    function showSlide(index) {
        carruselInner.style.transform = `translateX(-${index * 100}%)`;
        slides.forEach((slide, i) => {
            slide.classList.remove('active');
            if (i === index) {
                slide.classList.add('active');
            }
        });
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % slides.length;
        showSlide(currentIndex);
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + slides.length) % slides.length;
        showSlide(currentIndex);
    }

    function autoSlide() {
        nextSlide();
    }

    document.querySelector('.carrusel-control-prev').addEventListener('click', prevSlide);
    document.querySelector('.carrusel-control-next').addEventListener('click', nextSlide);

    setInterval(autoSlide, 3000);
});
