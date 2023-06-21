function getBotResponse(input) {
    //rock paper scissors
    if (input == "rock") {
        return "paper";
    } else if (input == "paper") {
        return "scissors";
    } else if (input == "scissors") {
        return "rock";
    }

    // Simple responses
    if (input == "안녕") {
        return "안녕하세요 반갑습니다! 궁금한것이 있으면 언제든 저에게 물어보셔도 됩니다!";
    } else if (input == "") {
        return "Talk to you later!";
    } else {
        return "Try asking something else!";
    }
}