import React from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import useToggle from '../../hooks/useToggle.js';

function PostMeta({ id, date, userId, star, viewCount }) {
    const isStared = useToggle();

    const onToggleStar = () => {
        isStared.toggle();
    };

    return (
        <Row>
            <Col>
                <h5>postId{id}</h5>
            </Col>
            <Col>
                <h5>date:{date}</h5>
            </Col>
            <Col>
                <h5>userId{userId}</h5>
            </Col>
            <Col>
                <h5>views:{viewCount}</h5>
            </Col>
            <Col>
                <Button
                    onClick={onToggleStar}
                    variant={isStared.state ? 'primary' : 'outline-primary'}
                >
                    ☆ {star + isStared.state}
                </Button>
            </Col>
        </Row>
    );
}

export default PostMeta;
