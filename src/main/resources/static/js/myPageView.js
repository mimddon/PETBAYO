window.onload = function () {
    const boxes = document.querySelectorAll('.user-pic-wrapper');
    boxes.forEach(box => {
        fetch("/api/users/img/" + box.id)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error: " + response.status);
                }
                const contentType = response.headers.get('content-type');
                return response.arrayBuffer()
                    .then(buffer => ({ buffer, contentType }));
            })
            .then(({ buffer, contentType }) => {
                const imgTag = document.createElement('img');
                const arrayBufferView = new Uint8Array(buffer);
                const blob = new Blob([arrayBufferView], { type: contentType });
                const imgUrl = URL.createObjectURL(blob);
                imgTag.src = imgUrl;
                box.appendChild(imgTag);
            })
            .catch(error => {
                console.error(error);
            });
    });
}
/*window.onload = function () {
    const box = document.querySelector('.img-area');
    fetch("api/users/img/" + box.id)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error: " + response.status);
            }
            const contentType = response.headers.get('content-type');
            return response.arrayBuffer()
                .then(buffer => ({ buffer, contentType }));
        })
        .then(({ buffer, contentType }) => {
            const imgTag = document.createElement('img');
            const arrayBufferView = new Uint8Array(buffer);
            const blob = new Blob([arrayBufferView], { type: contentType });
            const imgUrl = URL.createObjectURL(blob);
            imgTag.src = imgUrl;
            box.appendChild(imgTag);
        })
        .catch(error => {
            console.error(error);
        });
}*/