import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './log-user-info.reducer';
import { ILogUserInfo } from 'app/shared/model/log-user-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILogUserInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LogUserInfoDetail extends React.Component<ILogUserInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { logUserInfoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            LogUserInfo [<b>{logUserInfoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="userName">User Name</span>
              <UncontrolledTooltip target="userName">The firstname attribute.</UncontrolledTooltip>
            </dt>
            <dd>{logUserInfoEntity.userName}</dd>
            <dt>
              <span id="nickName">Nick Name</span>
            </dt>
            <dd>{logUserInfoEntity.nickName}</dd>
            <dt>
              <span id="email">Email</span>
            </dt>
            <dd>{logUserInfoEntity.email}</dd>
            <dt>
              <span id="phoneNumber">Phone Number</span>
            </dt>
            <dd>{logUserInfoEntity.phoneNumber}</dd>
            <dt>
              <span id="hireDate">Hire Date</span>
            </dt>
            <dd>
              <TextFormat value={logUserInfoEntity.hireDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/log-user-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/log-user-info/${logUserInfoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ logUserInfo }: IRootState) => ({
  logUserInfoEntity: logUserInfo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LogUserInfoDetail);
