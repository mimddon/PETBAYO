function getBotResponse(input) {
    //rock paper scissors
    if (input == "안녕하세요 지금 펫 돌봄 가능 한가요?") {
        return "네 가능합니다";
    } else if (input == "paper") {
        return "ㅇㅋ";
    } else if (input == "scissors") {
        return "rock";
    }

    // Simple responses
    if (input == "지금 펫 돌봄 가능 한가요?") {
        return "네 가능합니다";
    } else if (input == "지금 이쪽으로 와주세요") {
        return "넵";
    } else {
        return "Try asking something else!";
    }
}