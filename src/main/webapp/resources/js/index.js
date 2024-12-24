let clickListener = null
let canvas = null
let ctx = null
let lastR = null

function drawCanvas() {
    let r = document.getElementById('form1:sliderV').value;

    lastR = r
    canvas = document.getElementById('graphCanvas')
    ctx = canvas.getContext('2d')

    const width = canvas.width
    const height = canvas.height
    const centerX = width/2
    const centerY = height/2
    const scale = (width/2-40)/r

    ctx.clearRect(0, 0, width, height)

    ctx.fillStyle = 'rgba(0, 0, 255, 0.3)';

    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX + r * scale, centerY);
    ctx.lineTo(centerX, centerY - (r / 2) * scale);
    ctx.closePath();
    ctx.fill();

    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.arc(centerX, centerY, r * scale, 0, Math.PI / 2, false);
    ctx.closePath();
    ctx.fill();

    ctx.fillRect(centerX - r/2 * scale, centerY, r/2 * scale, r * scale);

    ctx.strokeStyle = 'black';
    ctx.lineWidth = 1;
    ctx.beginPath();
    ctx.moveTo(0, centerY);
    ctx.lineTo(width, centerY);
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX, height);
    ctx.stroke();

    ctx.beginPath();

    ctx.moveTo(width - 10, centerY - 5);
    ctx.lineTo(width, centerY);
    ctx.lineTo(width - 10, centerY + 5);

    ctx.moveTo(centerX - 5, 10);
    ctx.lineTo(centerX, 0);
    ctx.lineTo(centerX + 5, 10);
    ctx.stroke();

    ctx.fillStyle = 'black'
    ctx.font = '12px Arial'

    ctx.fillText('X', width-15, centerY-10)
    ctx.fillText(`-${r}`, centerX-r*scale-10, centerY+15)
    ctx.fillText(`-${r/2}`, centerX-(r/2)*scale-15, centerY+15)
    ctx.fillText(`${r/2}`, centerX+(r/2)*scale-5, centerY+15)
    ctx.fillText(`${r}`, centerX+r*scale-5, centerY+15)

    ctx.fillText('Y', centerX+10, 15)
    ctx.fillText(`${r}`, centerX+5, centerY-r*scale+5)
    ctx.fillText(`${r/2}`, centerX+5, centerY-(r/2)*scale+5)
    ctx.fillText(`${-r/2}`, centerX+5, centerY+(r/2)*scale+5)
    ctx.fillText(`-${r}`, centerX+5, centerY+r*scale+5)

    ctx.beginPath()
    ctx.moveTo(centerX-r*scale, centerY-5)
    ctx.lineTo(centerX-r*scale, centerY+5)
    ctx.moveTo(centerX-(r/2)*scale, centerY-5)
    ctx.lineTo(centerX-(r/2)*scale, centerY+5)
    ctx.moveTo(centerX+(r/2)*scale, centerY-5)
    ctx.lineTo(centerX+(r/2)*scale, centerY+5)
    ctx.moveTo(centerX+r*scale, centerY-5)
    ctx.lineTo(centerX+r*scale, centerY+5)

    ctx.moveTo(centerX-5, centerY-r*scale)
    ctx.lineTo(centerX+5, centerY-r*scale)
    ctx.moveTo(centerX-5, centerY-(r/2)*scale)
    ctx.lineTo(centerX+5, centerY-(r/2)*scale)
    ctx.moveTo(centerX-5, centerY+(r/2)*scale)
    ctx.lineTo(centerX+5, centerY+(r/2)*scale)
    ctx.moveTo(centerX-5, centerY+r*scale)
    ctx.lineTo(centerX+5, centerY+r*scale)

    ctx.stroke();

    if (clickListener) {
        canvas.removeEventListener('click', clickListener)
    }

    clickListener = function (event) {
        const rect = canvas.getBoundingClientRect()
        const x = event.clientX - rect.left
        const y = event.clientY - rect.top

        const relativeX = (x - centerX) / scale
        const relativeY = (centerY - y) / scale

        document.getElementById("hiddenForm:hiddenField1").value = relativeX
        document.getElementById("hiddenForm:hiddenField2").value = relativeY
        document.getElementById("hiddenForm:hiddenField3").value = r

        document.getElementById("hiddenForm:hiddenButton").click()

        setTimeout(() => {drawCanvas();},600)
    };

    canvas.addEventListener('click', clickListener);


    // тут нужно поставить все точки
    let table = document.getElementById('resultTable')

    for(let i = 0; i < 12; i++) {

        const firstRow = table.rows[i]

        const isHit = firstRow.cells[0].innerText
        const x = firstRow.cells[1].innerText
        const y = firstRow.cells[2].innerText

        let color;

        if (isHit === 'true') {
            color = "green"
        } else {
            color = "red"
        }

        drawPoint(color, r, x, y);
    }
}

function drawPoint(color, r, relativeX, relativeY){
    const width = canvas.width
    const height = canvas.height
    const centerX = width/2
    const centerY = height/2
    const scale = (width/2-40)/r

    ctx.fillStyle = color;
    const px = centerX + relativeX * scale
    const py = centerY - relativeY * scale
    ctx.beginPath();
    ctx.arc(px, py, 5, 0, Math.PI * 2);
    ctx.fill()
}

window.onload = function () {
    drawCanvas();
};