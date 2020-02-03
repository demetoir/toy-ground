import React, { useRef } from 'react';
import { Button, Form } from 'react-bootstrap';

function ReplyForm({
    postId,
    replyParentId,
    replyParentDepth,
    contents = '',
    onSubmit = () => {},
}) {
    const replyContentRef = useRef();

    // todo
    const onSubmitReply = () => {
        console.log(
            `${postId} ${replyParentId} ${replyParentDepth} ${replyContentRef.current.value}`
        );
    };

    return (
        <>
            <Form.Group controlId="exampleForm.ControlTextarea1">
                <Form.Control
                    as="textarea"
                    rows="3"
                    ref={replyContentRef}
                    defaultValue={contents}
                />
            </Form.Group>
            <Button
                onClick={() => {
                    onSubmitReply();
                    onSubmit();
                }}
                size={'sm'}
                variant={"success"}
            >
                submit reply
            </Button>
        </>
    );
}

export default ReplyForm;
