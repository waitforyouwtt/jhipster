import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class UserInfoDetail extends React.Component<IUserInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { userInfoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            UserInfo [<b>{userInfoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="userId">User Id</span>
              <UncontrolledTooltip target="userId">用户ID</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.userId}</dd>
            <dt>
              <span id="userName">User Name</span>
              <UncontrolledTooltip target="userName">人物名字</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.userName}</dd>
            <dt>
              <span id="nickName">Nick Name</span>
              <UncontrolledTooltip target="nickName">人物昵称</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.nickName}</dd>
            <dt>
              <span id="sex">Sex</span>
              <UncontrolledTooltip target="sex">性别</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.sex}</dd>
            <dt>
              <span id="ascription">Ascription</span>
              <UncontrolledTooltip target="ascription">归属门派</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.ascription}</dd>
            <dt>
              <span id="address">Address</span>
              <UncontrolledTooltip target="address">家庭住址</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.address}</dd>
            <dt>
              <span id="email">Email</span>
              <UncontrolledTooltip target="email">电子邮箱</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.email}</dd>
            <dt>
              <span id="phoneNumber">Phone Number</span>
              <UncontrolledTooltip target="phoneNumber">手机号</UncontrolledTooltip>
            </dt>
            <dd>{userInfoEntity.phoneNumber}</dd>
            <dt>
              <span id="birthday">Birthday</span>
              <UncontrolledTooltip target="birthday">生日</UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={userInfoEntity.birthday} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createTime">Create Time</span>
              <UncontrolledTooltip target="createTime">创建时间</UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={userInfoEntity.createTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateTime">Update Time</span>
              <UncontrolledTooltip target="updateTime">修改时间</UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={userInfoEntity.updateTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/user-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/user-info/${userInfoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ userInfo }: IRootState) => ({
  userInfoEntity: userInfo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserInfoDetail);
