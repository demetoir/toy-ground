import { Col, Row } from 'react-bootstrap';
import React from 'react';

function Box({ justifyContent = 'center', children }) {
    return (
        <Row>
            <Col
                style={{
                    justifyContent,
                    display: 'flex',
                    marginTop: '0.5rem',
                    marginBottom: '0.5rem',
                }}
            >
                {children}
            </Col>
        </Row>
    );
}

export default Box;
