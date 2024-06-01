
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
        } else {
            alert('error', 'Failed to fetch recently viewed workspaces', result.message);
        }
    } catch (error) {
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
    document.addEventListener('DOMContentLoaded', function() {
        fetchRecentlyViewedWorkspaces();
    });
