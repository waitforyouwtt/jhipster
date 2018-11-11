import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './user-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IUserInfoUpdateState {
  isNew: boolean;
}

export class UserInfoUpdate extends React.Component<IUserInfoUpdateProps, IUserInfoUpdateState> {
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
    values.birthday = new Date(values.birthday);
    values.createTime = new Date(values.createTime);
    values.updateTime = new Date(values.updateTime);

    if (errors.length === 0) {
      const { userInfoEntity } = this.props;
      const entity = {
        ...userInfoEntity,
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
    this.props.history.push('/entity/user-info');
  };

  render() {
    const { userInfoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="putongApp.userInfo.home.createOrEditLabel">Create or edit a UserInfo</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : userInfoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="user-info-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="userIdLabel" for="userId">
                    User Id
                  </Label>
                  <AvField id="user-info-userId" type="text" name="userId" />
                  <UncontrolledTooltip target="userIdLabel">用户ID</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="userNameLabel" for="userName">
                    User Name
                  </Label>
                  <AvField id="user-info-userName" type="text" name="userName" />
                  <UncontrolledTooltip target="userNameLabel">人物名字</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nickNameLabel" for="nickName">
                    Nick Name
                  </Label>
                  <AvField id="user-info-nickName" type="text" name="nickName" />
                  <UncontrolledTooltip target="nickNameLabel">人物昵称</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sexLabel">Sex</Label>
                  <AvInput
                    id="user-info-sex"
                    type="select"
                    className="form-control"
                    name="sex"
                    value={(!isNew && userInfoEntity.sex) || 'MAN'}
                  >
                    <option value="MAN">MAN</option>
                    <option value="WOMAN">WOMAN</option>
                  </AvInput>
                  <UncontrolledTooltip target="sexLabel">性别</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ascriptionLabel">Ascription</Label>
                  <AvInput
                    id="user-info-ascription"
                    type="select"
                    className="form-control"
                    name="ascription"
                    value={(!isNew && userInfoEntity.ascription) || 'SHENGTI'}
                  >
                    <option value="SHENGTI">SHENGTI</option>
                    <option value="YAOGUANG">YAOGUANG</option>
                    <option value="JIANGJIA">JIANGJIA</option>
                    <option value="YAOZU">YAOZU</option>
                    <option value="SANXIU">SANXIU</option>
                  </AvInput>
                  <UncontrolledTooltip target="ascriptionLabel">归属门派</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    Address
                  </Label>
                  <AvField id="user-info-address" type="text" name="address" />
                  <UncontrolledTooltip target="addressLabel">家庭住址</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    Email
                  </Label>
                  <AvField id="user-info-email" type="text" name="email" />
                  <UncontrolledTooltip target="emailLabel">电子邮箱</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneNumberLabel" for="phoneNumber">
                    Phone Number
                  </Label>
                  <AvField id="user-info-phoneNumber" type="text" name="phoneNumber" />
                  <UncontrolledTooltip target="phoneNumberLabel">手机号</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="birthdayLabel" for="birthday">
                    Birthday
                  </Label>
                  <AvInput
                    id="user-info-birthday"
                    type="datetime-local"
                    className="form-control"
                    name="birthday"
                    value={isNew ? null : convertDateTimeFromServer(this.props.userInfoEntity.birthday)}
                  />
                  <UncontrolledTooltip target="birthdayLabel">生日</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="createTimeLabel" for="createTime">
                    Create Time
                  </Label>
                  <AvInput
                    id="user-info-createTime"
                    type="datetime-local"
                    className="form-control"
                    name="createTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.userInfoEntity.createTime)}
                  />
                  <UncontrolledTooltip target="createTimeLabel">创建时间</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="updateTimeLabel" for="updateTime">
                    Update Time
                  </Label>
                  <AvInput
                    id="user-info-updateTime"
                    type="datetime-local"
                    className="form-control"
                    name="updateTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.userInfoEntity.updateTime)}
                  />
                  <UncontrolledTooltip target="updateTimeLabel">修改时间</UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/user-info" replace color="info">
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
  userInfoEntity: storeState.userInfo.entity,
  loading: storeState.userInfo.loading,
  updating: storeState.userInfo.updating
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
)(UserInfoUpdate);
