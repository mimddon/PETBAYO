function getKoreanAge(birthDate) {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const birthYear = parseInt(birthDate.substring(0, 4));
    const koreanAge = currentYear - birthYear + 1;
    return koreanAge + '세';
}