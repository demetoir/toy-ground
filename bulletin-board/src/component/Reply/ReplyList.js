import React from 'react';
import Reply from './Reply.js';

function ReplyList(props) {
    let { data } = props;

    return (
        <div>
            {data.map((x, i) => (
                <div key={i}>
                    <Reply {...x} />
                    <hr />
                </div>
            ))}
        </div>
    );
}

export default ReplyList;
