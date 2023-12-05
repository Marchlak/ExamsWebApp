document.addEventListener('DOMContentLoaded', function () {
    let createExamButton = document.querySelector('.create-exam-submit')
    let editExamButton = document.querySelector('.edit-exam')
    let createExamBox = document.querySelector('.create-exam-box')
    let editExamBox = document.querySelector('.edit-exam-box')
    let cancelCreateButton = document.querySelector('.cancel-create-exam')
    let cancelEditButton = document.querySelector('.cancel-edit-exam')
    let overlay = document.getElementById('overlay')

    createExamButton.addEventListener('click', function () {
        createExamBox.classList.toggle('hidden')
        overlay.classList.toggle('hidden')
    })

    editExamButton.addEventListener('click', function () {
        editExamBox.classList.toggle('hidden')
        overlay.classList.toggle('hidden')
    })

    cancelCreateButton.addEventListener('click', function () {
        createExamBox.classList.add('hidden')
        overlay.classList.add('hidden')
    })

    cancelEditButton.addEventListener('click', function () {
        editExamBox.classList.add('hidden')
        overlay.classList.add('hidden')
    })

    document.querySelector('.createExam').addEventListener('click', function() {
        document.getElementById('formularz').submit();
    });
})