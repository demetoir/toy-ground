import ReactPaginate from 'react-paginate';
import React from 'react';

function Paginate({
    pageCount,
    onPageChange,
    marginPagesDisplayed = 1,
    pageRangeDisplayed = 7,
    forcePage,
    initialPage
}) {
    return (
        <ReactPaginate
            pageCount={pageCount}
            marginPagesDisplayed={marginPagesDisplayed}
            pageRangeDisplayed={pageRangeDisplayed}
            onPageChange={onPageChange}
            forcePage={forcePage}
            initialPage={initialPage}
            containerClassName={'pagination'}
            subContainerClassName={'pagination'}
            previousLabel={'<'}
            previousClassName={'page-item'}
            previousLinkClassName={'page-link'}
            breakLabel={'...'}
            breakClassName={'page-item'}
            breakLinkClassName={'page-link'}
            nextLabel={'>'}
            nextClassName={'page-item'}
            nextLinkClassName={'page-link'}
            pageClassName={'page-item'}
            pageLinkClassName={'page-link'}
            activeClassName={'active'}
        />
    );
}

export default Paginate;
