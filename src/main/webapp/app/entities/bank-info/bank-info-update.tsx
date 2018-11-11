import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bank-info.reducer';
import { IBankInfo } from 'app/shared/model/bank-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBankInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBankInfoUpdateState {
  isNew: boolean;
}

export class BankInfoUpdate extends React.Component<IBankInfoUpdateProps, IBankInfoUpdateState> {
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
    values.createTime = new Date(values.createTime);
    values.updateTime = new Date(values.updateTime);

    if (errors.length === 0) {
      const { bankInfoEntity } = this.props;
      const entity = {
        ...bankInfoEntity,
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
    this.props.history.push('/entity/bank-info');
  };

  render() {
    const { bankInfoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="putongApp.bankInfo.home.createOrEditLabel">Create or edit a BankInfo</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bankInfoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="bank-info-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="bankIdLabel" for="bankId">
                    Bank Id
                  </Label>
                  <AvField id="bank-info-bankId" type="text" name="bankId" />
                  <UncontrolledTooltip target="bankIdLabel">记录Id</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="userIdLabel" for="userId">
                    User Id
                  </Label>
                  <AvField id="bank-info-userId" type="text" name="userId" />
                  <UncontrolledTooltip target="userIdLabel">用户Id</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="accountLabel" for="account">
                    Account
                  </Label>
                  <AvField id="bank-info-account" type="text" name="account" />
                  <UncontrolledTooltip target="accountLabel">开户账号</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="accountNameLabel" for="accountName">
                    Account Name
                  </Label>
                  <AvField id="bank-info-accountName" type="text" name="accountName" />
                  <UncontrolledTooltip target="accountNameLabel">开户名</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bankNameLabel" for="bankName">
                    Bank Name
                  </Label>
                  <AvField id="bank-info-bankName" type="text" name="bankName" />
                  <UncontrolledTooltip target="bankNameLabel">开户银行</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bankBranchNameLabel" for="bankBranchName">
                    Bank Branch Name
                  </Label>
                  <AvField id="bank-info-bankBranchName" type="text" name="bankBranchName" />
                  <UncontrolledTooltip target="bankBranchNameLabel">银行支行</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="amountLabel" for="amount">
                    Amount
                  </Label>
                  <AvField id="bank-info-amount" type="text" name="amount" />
                  <UncontrolledTooltip target="amountLabel">存款金额</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="createTimeLabel" for="createTime">
                    Create Time
                  </Label>
                  <AvInput
                    id="bank-info-createTime"
                    type="datetime-local"
                    className="form-control"
                    name="createTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.bankInfoEntity.createTime)}
                  />
                  <UncontrolledTooltip target="createTimeLabel">创建时间</UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="updateTimeLabel" for="updateTime">
                    Update Time
                  </Label>
                  <AvInput
                    id="bank-info-updateTime"
                    type="datetime-local"
                    className="form-control"
                    name="updateTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.bankInfoEntity.updateTime)}
                  />
                  <UncontrolledTooltip target="updateTimeLabel">修改时间</UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/bank-info" replace color="info">
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
  bankInfoEntity: storeState.bankInfo.entity,
  loading: storeState.bankInfo.loading,
  updating: storeState.bankInfo.updating
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
)(BankInfoUpdate);
