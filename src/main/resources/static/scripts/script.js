const transactType = document.getElementById("transact-type");

const paymentCard = document.querySelector(".payment-card");
const transferCard = document.querySelector(".transfer-card");
const depositCard = document.querySelector(".deposit-card");
const withdrawCard = document.querySelector(".withdraw-card");

const fund1 = document.getElementById('fund-1');
const fund2 = document.getElementById('fund-2');
const fund3 = document.getElementById('fund-3');
const fund4 = document.getElementById('fund-4');

transactType.addEventListener('change', () => {
    switch (transactType.value) {
        case "":
            paymentCard.style.display = "none";
            transferCard.style.display = "none";
            depositCard.style.display = "none";
            withdrawCard.style.display = "none";
        break;

        case "payment":
            paymentCard.style.display = "block";
            transferCard.style.display = "none";
            depositCard.style.display = "none";
            withdrawCard.style.display = "none";
        break;

        case "transfer":
            paymentCard.style.display = "none";
            transferCard.style.display = "block";
            depositCard.style.display = "none";
            withdrawCard.style.display = "none";
        break;

        case "deposit":
            paymentCard.style.display = "none";
            transferCard.style.display = "none";
            depositCard.style.display = "block";
            withdrawCard.style.display = "none";
        break;

        case "withdraw":
            paymentCard.style.display = "none";
            transferCard.style.display = "none";
            depositCard.style.display = "none";
            withdrawCard.style.display = "block";
        break;
    }
});

fund1.addEventListener('click', () => fillAndChangeMainIcons(fund1));
fund2.addEventListener('click', () => fillAndChangeMainIcons(fund2));
fund3.addEventListener('click', () => fillAndChangeMainIcons(fund3));
fund4.addEventListener('click', () => fillAndChangeMainIcons(fund4));

function fillAndChangeMainIcons(fund) {
    const isNotClicked = !fund.classList.contains('filled');
    let allFunds = document.getElementsByClassName('square-small');
    if (isNotClicked) {
        for (let i = 0; i < allFunds.length; i++) {
            const el = allFunds.item(i);
            const elIconName = fund.querySelector('a i').className;
            const elSumDesc = document.getElementById(fund.id + '-desc').innerHTML;
            const elSumAmount = document.getElementById(fund.id + '-amount').innerHTML;

            if (el.id === fund.id) {
                let bigSquareIcon = document.getElementById('square-big-icon');
                let smallIcon = document.getElementById('small-icon');
                let sumDesc = document.getElementById('sum-desc');
                let sumAmount = document.getElementById('sum-amount');

                bigSquareIcon.className = elIconName;
                smallIcon.className = elIconName;
                sumDesc.innerHTML = elSumDesc;
                sumAmount.innerHTML = elSumAmount;

                el.classList.add('filled');
            } else {
                el.classList.remove('filled');
            }
        }
    }
}