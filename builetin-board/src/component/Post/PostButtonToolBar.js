import { Button, ButtonToolbar, Col, Row } from 'react-bootstrap';
import React from 'react';

// TODO
const onEditPost = () => {
    console.log('onEditPost');
};

// TODO
const onDeletePost = () => {
    console.log('onDeletePost');
};

function PostButtonToolBar() {
    return (
        <Row>
            <Col>
                <ButtonToolbar>
                    <Button onClick={onEditPost} variant={'info'} size={'sm'}>
                        edit
                    </Button>
                    <Button
                        onClick={onDeletePost}
                        variant={'danger'}
                        size={'sm'}
                    >
                        delete{' '}
                    </Button>
                </ButtonToolbar>
            </Col>
        </Row>
    );
}

export default PostButtonToolBar;
