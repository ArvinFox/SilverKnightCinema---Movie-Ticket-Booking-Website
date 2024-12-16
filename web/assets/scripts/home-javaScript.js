//step 1: get DOM
let nextDom = document.getElementById('next');
let prevDom = document.getElementById('prev');

let carouselDom = document.querySelector('.carousel');
let SliderDom = carouselDom.querySelector('.carousel .list');
let thumbnailBorderDom = document.querySelector('.carousel .thumbnail');
let thumbnailItemsDom = thumbnailBorderDom.querySelectorAll('.item');
let timeDom = document.querySelector('.carousel .time');

thumbnailBorderDom.appendChild(thumbnailItemsDom[0]);
let timeRunning = 3000;
let timeAutoNext = 7000;

nextDom.onclick = function(){
    showSlider('next');    
}

prevDom.onclick = function(){
    showSlider('prev');    
}
let runTimeOut;
let runNextAuto = setTimeout(() => {
    next.click();
}, timeAutoNext)
function showSlider(type){
    let  SliderItemsDom = SliderDom.querySelectorAll('.carousel .list .item');
    let thumbnailItemsDom = document.querySelectorAll('.carousel .thumbnail .item');
    
    if(type === 'next'){
        SliderDom.appendChild(SliderItemsDom[0]);
        thumbnailBorderDom.appendChild(thumbnailItemsDom[0]);
        carouselDom.classList.add('next');
    }else{
        SliderDom.prepend(SliderItemsDom[SliderItemsDom.length - 1]);
        thumbnailBorderDom.prepend(thumbnailItemsDom[thumbnailItemsDom.length - 1]);
        carouselDom.classList.add('prev');
    }
    clearTimeout(runTimeOut);
    runTimeOut = setTimeout(() => {
        carouselDom.classList.remove('next');
        carouselDom.classList.remove('prev');
    }, timeRunning);

    clearTimeout(runNextAuto);
    runNextAuto = setTimeout(() => {
        next.click();
    }, timeAutoNext)
}
// =================================
function playVideo() {
    const trailerPopup = document.getElementById("trailerPopup");
    const trailerVideo = document.getElementById("trailerVideo");
    const closePopup = document.getElementById("closePopup");
  
    document.querySelectorAll(".trailer-btn[data-trailer]").forEach(button => {
      button.addEventListener("click", function () {
        const trailerUrl = button.getAttribute("data-trailer");
  
        trailerVideo.src = trailerUrl;
  
        trailerPopup.classList.add("visible");
        document.body.style.overflow = 'hidden';
      });
    });
  
    const closeTrailerPopup = () => {
      trailerPopup.classList.remove("visible");
      trailerVideo.src = "";
      document.body.style.overflow = '';
    };
  
    closePopup.addEventListener("click", closeTrailerPopup);
  
    trailerPopup.addEventListener("click", function (e) {
      if (e.target === trailerPopup) {
        closeTrailerPopup();
      }
    });
  }
  
  document.addEventListener('DOMContentLoaded', playVideo);