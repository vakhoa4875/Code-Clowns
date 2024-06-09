

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

function updateRecentlyViewedWorkspaces(workspaces) {
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
                    <a href="" class="" style="text-decoration: none; color: black;"><i class="fa fa-address-card me-2"
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
                    <a href="" class="" style="text-decoration: none; color: black;"><i class="fa fa-user me-2"
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
}

document.addEventListener('DOMContentLoaded', function() {
    fetchRecentlyViewedWorkspaces();
});
