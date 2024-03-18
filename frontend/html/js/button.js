const editButton = document.querySelector('.edit-button');
const profilePictureInput = document.querySelector('#profile-picture-input');

editButton.addEventListener('click', () => {
  profilePictureInput.click();
});

profilePictureInput.addEventListener('change', (event) => {
  const file = event.target.files[0];
  // 선택된 파일 처리 코드
  // 예: 이미지 미리보기 표시, 서버에 업로드
});