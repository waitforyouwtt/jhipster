import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './log-user-info.reducer';
import { ILogUserInfo } from 'app/shared/model/log-user-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILogUserInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILogUserInfoUpdateState {
  isNew: boolean;
}

export class LogUserInfoUpdate extends React.Component<ILogUserInfoUpdateProps, ILogUserInfoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    values.hireDate = new Date(values.hireDate);

    if (errors.length === 0) {
      const { logUserInfoEntity } = this.props;
      const entity = {
        ...logUserInfoEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/log-user-info');
  };

  render() {
    const { logUserInfoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="putongApp.logUserInfo.home.createOrEditLabel">Create or edit a LogUserInfo</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : logUserInfoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="log-user-info-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="userNameLabel" for="userName">
                    User Name
                  </Label>
                  <AvField id="log-user-info-userName" type="text" name="userName" />
                  <UncontrolledTooltip target="userNameLabel">The firstname attribute.</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nickNameLabel" for="nickName">
                    Nick Name
                  </Label>
                  <AvField id="log-user-info-nickName" type="text" name="nickName" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    Email
                  </Label>
                  <AvField id="log-user-info-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="phoneNumberLabel" for="phoneNumber">
                    Phone Number
                  </Label>
                  <AvField id="log-user-info-phoneNumber" type="text" name="phoneNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="hireDateLabel" for="hireDate">
                    Hire Date
                  </Label>
                  <AvInput
                    id="log-user-info-hireDate"
                    type="datetime-local"
                    className="form-control"
                    name="hireDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.logUserInfoEntity.hireDate)}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/log-user-info" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  logUserInfoEntity: storeState.logUserInfo.entity,
  loading: storeState.logUserInfo.loading,
  updating: storeState.logUserInfo.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LogUserInfoUpdate);
