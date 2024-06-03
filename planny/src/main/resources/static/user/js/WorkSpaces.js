async function getAllWorkSpaces() {
    try {
        const response = await axios.get('/api-public/workspaces/getEnableAllWorkSpaces');
        return response.data;
    } catch (error) {
        console.error('Lỗi khi gọi API:', error);
    }
}
async function displayWorkSpaces() {
    const data = await getAllWorkSpaces();
    const container = document.getElementById('workspacesContainer');
    const backgrounds = [
        'url(/user/img/BackG1.jpg)',
        'url(/user/img/BackG2.jpg)',
        'url(/user/img/BackG3.jpg)',
        'url(/user/img/BackG4.jpg)',
        'url(/user/img/BackG5.jpg)',
        'url(/user/img/BackG6.jpg)',
    ];
    if (data && data.data) {
        data.data.forEach((workspace, index) => {
            const workspaceElement = document.createElement('div');
            workspaceElement.className = 'col-12 col-md-3 mb-3 d-flex flex-column align-items-center';
            workspaceElement.addEventListener('click', event => {
                if (event.target.classList.contains('more-options-button')) {
                    event.stopPropagation();
                } else {
                    window.location.href = '/board';
                }
            });
            const containerDiv = document.createElement('div');
            containerDiv.className = 'containerss';
            const backgroundIndex = index % backgrounds.length;
            const backgroundDiv = document.createElement('div');
            backgroundDiv.className = 'hinhChuNhat';
            backgroundDiv.style.backgroundImage = backgrounds[backgroundIndex];
            backgroundDiv.style.width = '100%';
            backgroundDiv.style.height = '100px';
            backgroundDiv.style.backgroundSize = 'cover';

            const shortNameDiv = document.createElement('div');
            shortNameDiv.className = 'dongChu';
            shortNameDiv.style.fontSize = '15px';
            shortNameDiv.style.color = 'white';
            shortNameDiv.textContent = `${workspace.shortName}`;

            // Tạo nút 3 chấm
            const moreOptionsButton = document.createElement('div');
            moreOptionsButton.className = 'more-options-button';
            moreOptionsButton.innerHTML = '&#8942;'; // Mã HTML cho ký tự 3 chấm

            // Tạo menu dropdown
            const dropdownMenu = document.createElement('ul');
            dropdownMenu.className = 'dropdown-menu';
            dropdownMenu.innerHTML = `
             <li><a class="dropdown-item" href="#">Xóa</a></li>
`;
            dropdownMenu.style.position = 'absolute';
            dropdownMenu.style.top = '30px';
            dropdownMenu.style.right = '0';
            dropdownMenu.style.display = 'none';
            moreOptionsButton.addEventListener('click', () => {
                if (dropdownMenu.style.display === 'none') {
                    dropdownMenu.style.display = 'block';
                } else {
                    dropdownMenu.style.display = 'none';
                }
            });
            containerDiv.appendChild(backgroundDiv);
            containerDiv.appendChild(shortNameDiv);
            containerDiv.appendChild(moreOptionsButton);
            containerDiv.appendChild(dropdownMenu);

            workspaceElement.appendChild(containerDiv);
            container.appendChild(workspaceElement);
        });
    } else {
        container.textContent = 'Không có dữ liệu để hiển thị';
    }
}
displayWorkSpaces();
async function fetchBoards() {
    try {
        const response = await axios.get('/api-public/board/getEnableAllBoard');
        console.log(response.data);  // Kiểm tra dữ liệu trả về từ API
        const boards = response.data.data; // Điều chỉnh theo cấu trúc dữ liệu thực tế
        if (!Array.isArray(boards)) {
            throw new Error("Dữ liệu trả về không phải là mảng");
        }
        const boardContainer = document.getElementById('board-container');
        const backgrounds = [
            'url(/user/img/BackG1.jpg)',
            'url(/user/img/BackG2.jpg)',
            'url(/user/img/BackG3.jpg)',
            'url(/user/img/BackG4.jpg)',
            'url(/user/img/BackG5.jpg)',
            'url(/user/img/BackG6.jpg)',
        ];
        const groupedBoards = new Map();
        boards.forEach(board => {
            const workspaceName = board.workSpace.workspaceName;
            if (!groupedBoards.has(workspaceName)) {
                groupedBoards.set(workspaceName, []);
            }
            groupedBoards.get(workspaceName).push(board);
        });

        let index = 0;
        groupedBoards.forEach((boards, workspaceName) => {
            const workspaceNameElement = document.createElement('div');
            workspaceNameElement.className = 'd-flex flex-column flex-lg-row justify-content-between mt-2 mb-3 col-12 col-sm-12';
            workspaceNameElement.innerHTML = `<h5>${workspaceName}</h5>`;
            boardContainer.appendChild(workspaceNameElement);
            boards.forEach(board => {
                const boardElement = document.createElement('div');
                boardElement.className = 'col-12 col-md-6 mb-3 d-flex flex-column align-items-center';
                const randomBackground = backgrounds[index % backgrounds.length];
                boardElement.innerHTML = `
                    <div class="containerss" style="background-image: ${randomBackground};">
                        <div class="hinhChuNhat" style="width: 100%; height: 100px; background-size: cover;"></div>
                        <div class="dongChu" style="font-size: 15px;">ShortName: ${board.shortName}</div>
                        <div class="workspaceName" style="color: white">${board.workSpace.shortName}</div>
                    </div>
                `;
                boardContainer.appendChild(boardElement);
                index++;
            });
        });
    } catch (error) {
        console.error('Error fetching boards:', error);
    }
}
window.onload = fetchBoards;

async function fetchWorkspaceIds() {
    try {
        const response = await axios.get('/api-public/workspaces/getEnableAllWorkSpaces');
        return response.data;
    } catch (error) {
        console.error('Error fetching workspaceId:', error);
        throw error;
    }
}
fetchWorkspaceIds()
    .then(response => {
        const workspaceIds = response.data; // Lấy mảng workspaceIds từ trường 'data' của response
        const workspaceDropdown = document.getElementById('workSpaceId');
        if (Array.isArray(workspaceIds)) {
            workspaceIds.forEach(workspace => {
                const option = document.createElement('option');
                option.value = workspace.workspaceId;
                option.textContent = workspace.workspaceName;
                workspaceDropdown.appendChild(option);
            });
        } else {
            console.error('WorkspaceIds is not an array:', workspaceIds);
        }
    })
    .catch(error => console.error('Error fetching and updating workspaceId:', error));

async function saveBoard(workspaceId) {
    try {
        const boardName = document.getElementById('boardName').value;
        const shortName = document.getElementById('shortNameBoard').value;
        const slugUrl = document.getElementById('slugUrl').value;
        const visibility = document.getElementById('visibility').value;
        if (!workspaceId) {
            console.error('workSpaceId is null or undefined.');
            return;
        }
        const response = await axios.post('/api-public/board/saveAllBoard', {
            workSpaceId: parseInt(workspaceId),
            boardName: boardName,
            shortName: shortName,
            slugUrl: slugUrl,
            visibility: visibility
        });
        $('#staticBackdrop').modal('hide');
        Swal.fire({
            position: 'between',
            icon: 'success',
            title: 'Lưu thành công',
            showConfirmButton: false,
            timer: 1500
        });
        setTimeout(() => {
            window.location.reload();
        }, 1500);
    } catch (error) {
        verificationForm();
        console.error('Error saving board:', error);
        throw error;
    }
}
const showErrorAlert = (message) => {
    Swal.fire({
        icon: "error",
        title: "Lỗi",
        text: message,
    });
}

const verificationForm = () => {
    const boardName = document.getElementById('boardName').value;
    const shortNameBoard = document.getElementById('shortNameBoard').value;
    const slugUrl = document.getElementById('slugUrl').value;
    if (boardName === '') {
        showErrorAlert("Vui lòng nhập board name!");
        document.getElementById('boardName').focus();
        return false; // Prevent form submission
    } else if (shortNameBoard === '') {
        showErrorAlert("Vui lòng nhập short name!");
        document.getElementById('shortNameBoard').focus();
        return false;
    } else if (slugUrl === '') {
        showErrorAlert("Vui lòng nhập slug URL!");
        document.getElementById('slugUrl').focus();
        return false;
    }
}
document.getElementById('saveButton').addEventListener('click', function() {
    const workspaceId = document.getElementById('workSpaceId').value;
    saveBoard(workspaceId);
});
