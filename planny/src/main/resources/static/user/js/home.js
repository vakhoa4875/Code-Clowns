
    verificationForm = () => {
    let message="Please Input Value";
    if ($('#workspacename').val()===''){
        alert(message);
        $('#workspacename').focus();
        return false;
    } else if ($('#shortname').val()===''){
        alert(message);
        $('#shortname').focus();
          return false;
    } else if ($('website').val()===''){
        alert(message);
        $('#website').focus();
          return false;
    } else if ($('#description').val()===''){
        alert(message);
        $('#description').focus()
          return false;
    }
    return true;
}
    btnClear_Click = () => {
    data = {
       workSpaceName: '' ,
        shortName: '' ,
        website: '',
        description: ''
    };
    fillForm(data)

}
    fillForm = (data) => {
       $('#workspacename').val(data.workSpaceName) ;
       $('#shortname').val(data.shortName);
       $('#website').val(data.website);
       $('#description').val(data.description);
    }
async function submitForm(event) {
    event.preventDefault();
      if (!verificationForm()) {
        return;
    }
    const formData = {
        workspaceName: document.getElementById('workspacename').value,
        shortName: document.getElementById('shortname').value,
        website: document.getElementById('website').value,
        description: document.getElementById('description').value
    };

    try {
        const response = await axios.post('/api-public/workspace/PostSaveWorkSpace', formData);
        const result = response.data;

        if (result.status === 'success') {
            swal("Workspace created successfully!");
        } else {
            swal("Failed to create workspace", result.message);
        }
    } catch (error) {
        console.error('Error:', error);
        swal('An error occurred while creating the workspace.');
    }
}
    async function fetchRecentlyViewedWorkspaces() {
    try {
        const response = await axios.get('/api-public/workspace/recently-viewed');
        const result = response.data;

        if (result.status === 'success') {
            updateRecentlyViewedWorkspaces(result.data);
            updateRecentlyViewedWorkspacesForSideBar(result.data)
        } else {
            alert('error', 'Failed to fetch recently viewed workspaces', result.message);
        }
    } catch ( error ) {
        console.error('Error:', error);
        alert('error', 'An error occurred', 'An error occurred while fetching recently viewed workspaces.');
    }
}

// Function to update the recently viewed workspaces in the HTML
function updateRecentlyViewedWorkspaces(workspaces) {
    const container = document.getElementById('recentlyViewedContainer');
    container.innerHTML = ''; // Clear existing content

    workspaces.forEach(workspace => {
        const workspaceHtml = `        
        <div  class="col-12  d-flex flex-column align-items-center" onclick="window.location.href='/board'">     
        <div class="containerss">
        <div class="hinhChuNhat"
        style="background-image: url(https://trello-backgrounds.s3.amazonaws.com/575584dacedaafdf0d8660c2/480x272/02a67bbc2d5b879d912dad85eb5f3a05/asset_3.png); width: 100%; height: 100px; background-size: cover;"></div>
        <div class="dongChu" style="font-size: 15px;">   ${workspace.workspaceName}</div>
        </div>
        </div>
                                                     
        `;
        container.innerHTML += workspaceHtml;
    });
}
// Function get all board from workspace
    async function fetchAllBoardFromWorkSpace(workspaceId){
        const response = await axios.get(`/api-public/board/getInformationBoard?workspaceId=${workspaceId}`);
        const result = response.data;
        if (result.status === 'success'){
            updateAllBoardFromWorkSpace(result.data);
        } else {
            alert("fail to fetch when call api /api-public/board/getInformationBoard")
        }
    }
  function updateAllBoardFromWorkSpace(boards) {
  const container = document.getElementById('board-container');
  let boardHtml = '';

  boards.forEach((board, index) => {
        boardHtml += `
            <div class="col-12 col-md-6 mb-3 d-flex flex-column align-items-center" onclick="window.location.href='/board'">
            <div class="containerss">
            <div class="hinhChuNhat" style="background-image: url(https://trello-backgrounds.s3.amazonaws.com/575584dacedaafdf0d8660c2/480x272/02a67bbc2d5b879d912dad85eb5f3a05/asset_3.png); width: 100%; height: 100px; background-size: cover;"></div>
            <div class="dongChu" style="font-size: 15px;">${board.boardName}</div>
            </div>
            </div> 
    `;
  });

  container.innerHTML = `<div class="task_board mt-3 row d-flex col-sm-12">${boardHtml}</div>`;
}
function updateRecentlyViewedWorkspacesForSideBar(workspaces) {
    const container = document.getElementById('accordionFlushExample');
    container.innerHTML = ''; // Clear existing content
    workspaces.forEach((workspace, index) => {
        const workspaceHtml = `
                <div class="accordion-item">
                <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#flush-collapseOne-${index}" aria-expanded="false" aria-controls="flush-collapseOne-${index}"
                style="background-color:rgb(133, 184, 255);">
                <h6> <i class="fa fa-users me-2" aria-hidden="true"></i>${workspace.workspaceName}</h6>
                </button>
                </h2>
                <div id="flush-collapseOne-${index}" class="accordion-collapse collapse baseball-link"
                 data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                <a href="" class="board-link" id="${workspace.workspaceId}" style="text-decoration: none; color: black;"><i class="fa fa-address-card me-2"
                aria-hidden="true"></i>Board</a>
                </div>
                </div>
                <div id="flush-collapseOne-${index}" class="accordion-collapse collapse baseball-link"
                data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                <a href="" class="" style="text-decoration: none; color: black;"><i class="fa fa-star me-2"
                aria-hidden="true"></i>Highlights</a>
                </div>
                </div>
                <div id="flush-collapseOne-${index}" class="accordion-collapse collapse baseball-link"
                data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                <a href="/workspace" class="" style="text-decoration: none; color: black;"><i class="fa fa-eye me-2"
                aria-hidden="true"></i>Views</a>
                </div>
                </div>
                <div id="flush-collapseOne-${index}" class="accordion-collapse collapse baseball-link"
                data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                <a href="" class="member-link" id="${workspace.workspaceId}"  style="text-decoration: none; color: black;"><i class="fa fa-user me-2"
                aria-hidden="true"></i>Member</a>
                </div>
                </div>
                <div id="flush-collapseOne-${index}" class="accordion-collapse collapse baseball-link"
                data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                <a href="" class="" style="text-decoration: none; color: black;"><i class="fa fa-cogs me-2"
                aria-hidden="true"></i>Setting</a>
                </div>
                </div>
                </div>
        `;
        container.innerHTML += workspaceHtml;
    });
    // Add event listener for member links

    container.addEventListener('click', function(event) {
        if (event.target && event.target.classList.contains('member-link')) {
        event.preventDefault();
        const workspaceId = event.target.getAttribute('id');
        fetchMembers(workspaceId);
        console.log("thanh cpmg")
    } else if (event.target && event.target.classList.contains('board-link')){
        event.preventDefault();
        const workspaceId = event.target.getAttribute('id');
        fetchAllBoardFromWorkSpace(workspaceId);
        console.log("thanh cpmg")
        }
});

}
    async function fetchMembers(workspaceId) {
            try {
                const response = await axios.get(`/api-public/collaborators/getInformation?workspaceId=${workspaceId}`);
                const result = response.data;
                    console.log(result)
                if (result.status === 'success') {
                    updateMemberTable(result.data);
                } else {
                    alert('Failed to fetch members: ' + result.message);
                }
                } catch (error) {
                console.error('Error:', error);
                alert('An error occurred while fetching member information.');
                }
        }

     async function fetchMembersByWorkSpaceInBoard(workspaceId) {
            try {
                const response = await axios.get(`/api-public/member/getByworkspace?workspaceId=${workspaceId}`);
                const result = response.data;
                    console.log(result)
                if (result.status === 'success') {
                    updateMemberTable(result.data);
                } else {
                    alert('Failed to fetch members: ' + result.message);
                }
                } catch (error) {
                console.error('Error:', error);
                alert('An error occurred while fetching member information.');
                }
        }

        function updateMemberTable(members) {
    const tableBody = document.getElementById('memberTable');
    tableBody.innerHTML = `
                <div class="search-bar">
                <input type="search" id="searchInput" placeholder="Lọc theo tên">
                </div>
                <ul class="user-list" id="userList">
               </ul>
    `; // Clear existing content

    const userList = document.getElementById('userList');

    function renderMembers(filteredMembers) {
        userList.innerHTML = ''; // Clear existing list
        filteredMembers.forEach(member => {
                const rowHtml = ` 
                <hr>   
                <div class="user-info">
                <img src="https://trello-logos.s3.amazonaws.com/5637d7c208cebebdb7a2d9670a583dfb/170.png" alt="${member.fullname}" class="user-avatar">
                <div>
                <span class="user-name">${member.fullname}</span>
                </div>
                <span class="user-name">|</span>
                <span class="user-name">${member.email}</span>
                </div>
                <span class="user-last-active">Lần hoạt động gần nhất</span>
                <div class="btn-group">
                <button class="btn-remove">X Loại bỏ...</button>
                <div class="dropdown">
                <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false" style="opacity: 0.9;font-size: 10px;">
                Thông tin
                </button>
                <div id="boarOfUser"></div>
                </div>
                </div>
            `;
            userList.innerHTML += rowHtml;
        });
    }

    // Initial render
    renderMembers(members);

    // Search functionality
    const searchInput = document.getElementById('searchInput');
    searchInput.addEventListener('input', (event) => {
        const searchTerm = event.target.value.toLowerCase();
        const filteredMembers = members.filter(member => member.fullname.toLowerCase().includes(searchTerm));
        renderMembers(filteredMembers);
    });
}

        document.addEventListener('DOMContentLoaded', function() {
        fetchRecentlyViewedWorkspaces();
    });
