//tradeRegist
function tradeRegist() {
	
	let carName = document.getElementById('carName').value;
	let price = document.getElementById('price').value;
	let year = document.getElementById('year').value;
	let fuel = document.getElementById('fuel').value;
	let mileage = document.getElementById('mileage').value;
	let region = document.getElementById('region').value;
	let displacement = document.getElementById('displacement').value;
	let model = document.getElementById('model').value;
	let color = document.getElementById('color').value;
	let accident = document.getElementById('accident').value;
	let photo = document.getElementById('photo').value;
	let carDes = document.getElementById('carDes').value;
	
	if(con_check == true && carName == '' || price == '' || year == '' || fuel == '' || mileage == '' || region == ''
	|| displacement == '' || model == '' || color == '' || accident == '' || photo == '' || carDes == '') {
		alert('차량의 데이터를 모두 입력해주세요.');
		return false;
	}
	
}

//tradeUpdate
function tradeUpdate() {
	
	let carName = document.getElementById('carName').value;
	let price = document.getElementById('price').value;
	let year = document.getElementById('year').value;
	let fuel = document.getElementById('fuel').value;
	let mileage = document.getElementById('mileage').value;
	let region = document.getElementById('region').value;
	let displacement = document.getElementById('displacement').value;
	let model = document.getElementById('model').value;
	let color = document.getElementById('color').value;
	let accident = document.getElementById('accident').value;
	let photo = document.getElementById('photo').value;
	let carDes = document.getElementById('carDes').value;
	
	if(con_check == true && carName == '' || price == '' || year == '' || fuel == '' || mileage == '' || region == ''
	|| displacement == '' || model == '' || color == '' || accident == '' || photo == '' || carDes == '') {
		alert('차량의 데이터를 모두 입력해주세요.');
		return false;
	}
	
}

//tradeList
function tradeSearch() {
	
	let searchValue = document.getElementById('searchValue').value;
	
	if(searchValue == '') {
		alert('검색어를 입력해주세요.');
	}
	
}