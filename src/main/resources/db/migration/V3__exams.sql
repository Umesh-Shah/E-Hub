CREATE TABLE exam (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255)  NOT NULL,
    description      VARCHAR(1000),
    duration_minutes INT           NOT NULL,
    total_marks      INT           NOT NULL,
    CONSTRAINT uq_exam_name UNIQUE (name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE question (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    exam_id        BIGINT       NOT NULL,
    question_text  VARCHAR(1000) NOT NULL,
    option_1       VARCHAR(255) NOT NULL,
    option_2       VARCHAR(255) NOT NULL,
    option_3       VARCHAR(255) NOT NULL,
    option_4       VARCHAR(255) NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    marks          INT          NOT NULL,
    CONSTRAINT fk_question_exam FOREIGN KEY (exam_id) REFERENCES exam (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE exam_attempt (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT      NOT NULL,
    exam_id      BIGINT      NOT NULL,
    status       VARCHAR(20) NOT NULL,
    started_at   DATETIME    NOT NULL,
    submitted_at DATETIME,
    score        INT,
    CONSTRAINT fk_exam_attempt_user FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_exam_attempt_exam FOREIGN KEY (exam_id) REFERENCES exam (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE exam_answer (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    attempt_id        BIGINT       NOT NULL,
    question_id       BIGINT       NOT NULL,
    submitted_answer  VARCHAR(255) NOT NULL,
    is_correct         BOOLEAN      NOT NULL,
    CONSTRAINT fk_exam_answer_attempt FOREIGN KEY (attempt_id) REFERENCES exam_attempt (id),
    CONSTRAINT fk_exam_answer_question FOREIGN KEY (question_id) REFERENCES question (id),
    CONSTRAINT uq_exam_answer_attempt_question UNIQUE (attempt_id, question_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE exam_result (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT   NOT NULL,
    exam_id      BIGINT   NOT NULL,
    attempt_id   BIGINT   NOT NULL,
    score        INT      NOT NULL,
    completed_at DATETIME NOT NULL,
    CONSTRAINT fk_exam_result_user FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_exam_result_exam FOREIGN KEY (exam_id) REFERENCES exam (id),
    CONSTRAINT fk_exam_result_attempt FOREIGN KEY (attempt_id) REFERENCES exam_attempt (id),
    CONSTRAINT uq_exam_result_attempt UNIQUE (attempt_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
