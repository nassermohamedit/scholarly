-- memberships table
CREATE TABLE memberships (
    profile_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    power SMALLINT,
    CONSTRAINT fk_profile FOREIGN KEY(profile_id) REFERENCES profiles(id),
    CONSTRAINT fk_topic_membership FOREIGN KEY(topic_id) REFERENCES topics(id),
    CONSTRAINT unique_profile_topic UNIQUE (profile_id, topic_id)
);

CREATE TABLE join_requests (
    profile_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    CONSTRAINT fk_profile_requests FOREIGN KEY(profile_id) REFERENCES profiles(id),
    CONSTRAINT fk_topic_requests FOREIGN KEY(topic_id) REFERENCES topics(id),
    CONSTRAINT unique_profile_topic_requests UNIQUE (profile_id, topic_id)
);