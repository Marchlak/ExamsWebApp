document.addEventListener('DOMContentLoaded', function () {
    let createExamButton = document.querySelector('.create-exam-submit')
    let examBox = document.querySelector('.exam-box')
    let overlay = document.getElementById('overlay')
    let cancelButton = document.querySelector('.cancel')

    createExamButton.addEventListener('click', function () {
        examBox.classList.toggle('hidden')
        overlay.classList.toggle('hidden')
    })

    cancelButton.addEventListener('click', function () {
        examBox.classList.add('hidden')
        overlay.classList.add('hidden')
    })
})